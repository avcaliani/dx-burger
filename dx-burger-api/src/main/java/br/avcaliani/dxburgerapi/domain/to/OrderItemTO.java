package br.avcaliani.dxburgerapi.domain.to;

import br.avcaliani.dxburgerapi.domain.entity.OrderIngredient;
import br.avcaliani.dxburgerapi.domain.entity.OrderItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Order Item Entity.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
public class OrderItemTO {

    private Long id;
    private BurgerTO burger;
    List<OrderIngredientTO> ingredients;

    /**
     * Default Constructor.
     */
    public OrderItemTO() { }

    /**
     * Entity Constructor.
     *
     * @param entity {@link OrderItem} Entity.
     */
    public OrderItemTO(OrderItem entity) {

        if (entity == null)
            return;

        this.id = entity.getId();

        if (entity.getBurger() != null)
            this.burger = new BurgerTO(entity.getBurger().getId(), entity.getBurger().getName());

        List<OrderIngredient> items = entity.getIngredients();
        if (items != null && !items.isEmpty()) {
            this.ingredients = new ArrayList<>();
            items.forEach((OrderIngredient i) -> this.ingredients.add(new OrderIngredientTO(i)));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemTO that = (OrderItemTO) o;
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
