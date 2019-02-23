package br.avcaliani.dxburgerapi.domain.to;

import br.avcaliani.dxburgerapi.domain.entity.OrderIngredient;
import lombok.Data;

import java.util.Objects;

/**
 * Order Ingredient Entity.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
public class OrderIngredientTO {

    private Long id;
    private Integer quantity;
    private IngredientTO ingredient;

    /**
     * Default Constructor.
     */
    public OrderIngredientTO() { }

    /**
     * Entity Constructor.
     *
     * @param entity {@link OrderIngredient} Entity.
     */
    public OrderIngredientTO(OrderIngredient entity) {

        if (entity == null)
            return;

        this.id = entity.getId();
        this.quantity = entity.getQuantity();

        if (entity.getIngredient() != null)
            this.ingredient = new IngredientTO(entity.getIngredient());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderIngredientTO that = (OrderIngredientTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
