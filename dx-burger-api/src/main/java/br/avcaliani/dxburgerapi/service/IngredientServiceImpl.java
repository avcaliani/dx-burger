package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.Ingredient;
import br.avcaliani.dxburgerapi.domain.to.IngredientTO;
import br.avcaliani.dxburgerapi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Ingredient Service Implementation.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository repository;

    /**
     * @see IngredientService#find()
     */
    @Override
    public List<IngredientTO> find() {
        return parse(repository.findAll());
    }

    /**
     * Return a list of {@link IngredientTO} based on a list of {@link Ingredient}.
     *
     * @param ingredients List of {@link Ingredient}.
     * @return List of {@link IngredientTO}.
     */
    private List<IngredientTO> parse(List<Ingredient> ingredients) {
        if (ingredients == null || ingredients.isEmpty())
            return null;
        List<IngredientTO> tos = new ArrayList<>();
        ingredients.forEach((Ingredient i) -> tos.add(new IngredientTO(i)));
        return tos;
    }
}
