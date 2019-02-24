package br.avcaliani.dxburgerapi.domain.entity;

import br.avcaliani.dxburgerapi.domain.to.OrderItemTO;
import br.avcaliani.dxburgerapi.domain.to.OrderTO;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Order Entity.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
@Entity
@Table(name = "purchase_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, columnDefinition = "DECIMAL(9,2)")
    private Double total;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false, columnDefinition = "DECIMAL(9,2)")
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    /**
     * Default Constructor.
     */
    public Order() { }

    /**
     * TO Constructor.
     *
     * @param to {@link OrderTO} TO.
     */
    public Order(OrderTO to) {

        if (to == null)
            return;

        this.id = to.getId();
        this.total = to.getTotal();
        this.creationDate = to.getCreationDate();
        this.discount = to.getDiscount();

        if (to.getUser() != null)
            this.user = new User(to.getUser());

        List<OrderItemTO> orderItems = to.getItems();
        if (orderItems != null && !orderItems.isEmpty()) {
            this.items = new ArrayList<>();
            orderItems.forEach((OrderItemTO i) -> this.items.add(new OrderItem(i)));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
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
