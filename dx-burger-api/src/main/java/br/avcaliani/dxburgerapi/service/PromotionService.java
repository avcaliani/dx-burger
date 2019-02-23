package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.to.IngredientTO;

import java.util.List;

/**
 * Ingredient Service.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface IngredientService {

    /**
     * Return a list of Ingredients.
     *
     * @return {@link List} of {@link IngredientTO}.
     */
    public List<IngredientTO> find();
}
