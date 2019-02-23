package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.Burger;
import br.avcaliani.dxburgerapi.domain.entity.BurgerIngredient;
import br.avcaliani.dxburgerapi.domain.entity.Ingredient;
import br.avcaliani.dxburgerapi.domain.to.BurgerTO;
import br.avcaliani.dxburgerapi.repository.BurgerRepository;
import br.avcaliani.dxburgerapi.repository.IngredientRepository;
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
    private IngredientService ingredientService;

    /**
     * @see BurgerService#find()
     */
    @Override
    public List<BurgerTO> find() {
        return repository.find();
    }

    /**
     * @see BurgerService#find(Long)
     */
    @Override
    public BurgerTO find(Long id) {

        if (id == null || id < 0)
            return null;

        Optional<Burger> opt = repository.findById(id);
        if (!opt.isPresent())
            return null;

        final Burger burger = opt.get();
        final List<String> currentIngredients = new ArrayList<>();
        if (burger.getIngredients() != null && !burger.getIngredients().isEmpty())
            burger.getIngredients().forEach((BurgerIngredient i) -> {
                if (i.getIngredient() != null)
                    currentIngredients.add(i.getIngredient().getName());
            });
        else
            burger.setIngredients(new ArrayList<>());

        List<Ingredient> ingredients = this.ingredientService.findMissing(currentIngredients);
        ingredients.forEach((Ingredient i) -> burger.getIngredients().add(new BurgerIngredient(0, i)));

        return new BurgerTO(burger);
    }
}
