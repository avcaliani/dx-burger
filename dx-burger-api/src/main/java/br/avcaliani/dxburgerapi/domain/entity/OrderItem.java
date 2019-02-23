package br.avcaliani.dxburgerapi.domain.entity;

import br.avcaliani.dxburgerapi.domain.to.OrderIngredientTO;
import br.avcaliani.dxburgerapi.domain.to.OrderItemTO;
import lombok.Data;

import javax.persistence.*;
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
@Entity
@Table(name = "purchase_order_item")
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "burger_id")
    private Burger burger;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<OrderIngredient> ingredients;

    /**
     * Default Constructor.
     */
    public OrderItem() { }

    /**
     * TO Constructor.
     *
     * @param to {@link OrderItemTO} Entity.
     */
    public OrderItem(OrderItemTO to) {

        if (to == null)
            return;

        this.id = to.getId();

        if (to.getBurger() != null)
            this.burger = new Burger(to.getBurger());

        List<OrderIngredientTO> items = to.getIngredients();
        if (items != null && !items.isEmpty()) {
            this.ingredients = new ArrayList<>();
            items.forEach((OrderIngredientTO i) -> this.ingredients.add(new OrderIngredient(i)));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem that = (OrderItem) o;
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
