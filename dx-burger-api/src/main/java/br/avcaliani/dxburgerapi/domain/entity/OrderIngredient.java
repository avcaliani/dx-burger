package br.avcaliani.dxburgerapi.domain.entity;

import br.avcaliani.dxburgerapi.domain.to.OrderIngredientTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * Order Ingredient Entity.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
@Entity
@Table(name = "purchase_order_ingredient")
public class OrderIngredient {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem item;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    /**
     * Default Constructor.
     */
    public OrderIngredient() { }

    /**
     * TO Constructor.
     *
     * @param to {@link OrderIngredientTO} TO.
     */
    public OrderIngredient(OrderIngredientTO to) {

        if (to == null)
            return;

        this.id = to.getId();
        this.quantity = to.getQuantity();

        if (to.getIngredient() != null)
            this.ingredient = new Ingredient(to.getIngredient());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderIngredient that = (OrderIngredient) o;
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
