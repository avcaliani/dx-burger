package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.Burger;
import br.avcaliani.dxburgerapi.domain.to.BurgerIngredientTO;
import br.avcaliani.dxburgerapi.domain.to.BurgerTO;
import br.avcaliani.dxburgerapi.domain.to.IngredientTO;
import br.avcaliani.dxburgerapi.repository.BurgerIngredientRepository;
import br.avcaliani.dxburgerapi.repository.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Burger Service Implementation.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Service
public class BurgerServiceImpl implements BurgerService {

    @Autowired
    private BurgerRepository repository;

    @Autowired
    private BurgerIngredientRepository biRepository;

    @Autowired
    private IngredientService ingredientService;

    /**
     * @see BurgerService#find()
     */
    @Override
    public List<BurgerTO> find() {
        List<BurgerTO> burgers = this.repository.find();
        burgers.forEach(
                (BurgerTO burger) -> burger.setPrice(biRepository.getBurgerPrice(burger.getId()))
        );
        return burgers;
    }

    /**
     * @see BurgerService#find(Long)
     */
    @Override
    public BurgerTO find(Long id) {

        if (id == null || id.longValue() < 0)
            return null;

        Optional<Burger> opt = this.repository.findById(id);
        if (!opt.isPresent())
            return null;

        final BurgerTO burger = new BurgerTO(opt.get());
        final List<String> currentIng = new ArrayList<>();

        List<BurgerIngredientTO> burgerIng = burger.getIngredients();
        if (burgerIng != null && !burgerIng.isEmpty())
            burgerIng.forEach((BurgerIngredientTO bi) -> {
                if (bi.getIngredient() != null) currentIng.add(bi.getIngredient().getName());
            });
        else
            burger.setIngredients(new ArrayList<>());

        List<IngredientTO> ingredients = this.ingredientService.findMissing(currentIng);
        ingredients.forEach(
                (IngredientTO i) -> burger.getIngredients().add(new BurgerIngredientTO(0, i))
        );

        burger.setPrice(this.biRepository.getBurgerPrice(burger.getId()));
        return burger;
    }
}
