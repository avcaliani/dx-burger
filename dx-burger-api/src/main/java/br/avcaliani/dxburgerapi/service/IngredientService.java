package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.Ingredient;
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

    /**
     * Return a list of missing ingredients based on {@code names} list.
     *
     * @param names {@link List} of Ingredient names.
     * @return {@link List} of {@link IngredientTO}.
     */
    public List<IngredientTO> findMissing(List<String> names);

    /**
     * Return ingredient price based on ingredient id.
     *
     * @param id Ingredient Id.
     * @return Ingredient Price.
     */
    public Double getPrice(Long id);
}
