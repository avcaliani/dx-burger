package br.avcaliani.dxburgerapi.domain.to;

import br.avcaliani.dxburgerapi.domain.entity.BurgerIngredient;
import lombok.Data;

/**
 * Burger Ingredient Transfer Object.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
public class BurgerIngredientTO {

    private Integer quantity;
    private IngredientTO ingredient;

    /**
     * Default Constructor.
     */
    public BurgerIngredientTO() { }

    /**
     * Entity Constructor.
     *
     * @param entity {@link BurgerIngredient} Entity.
     */
    public BurgerIngredientTO(BurgerIngredient entity) {

        if (entity == null)
            return;

        this.quantity = entity.getQuantity();
        if (entity.getIngredient() != null)
            this.ingredient = new IngredientTO(entity.getIngredient());
    }

}
