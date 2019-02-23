package br.avcaliani.dxburgerapi.domain.to;

import br.avcaliani.dxburgerapi.domain.entity.Order;
import br.avcaliani.dxburgerapi.domain.entity.OrderItem;
import br.avcaliani.dxburgerapi.domain.entity.User;
import lombok.Data;

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
public class OrderTO {

    private Long id;
    private Double total;
    private Double discount;
    private Date creationDate;
    private UserTO user;
    private List<OrderItemTO> items;

    /**
     * Default Constructor.
     */
    public OrderTO() { }

    /**
     * Simplified Order.
     *
     * @param id           Order Id.
     * @param total        Order Total.
     * @param discount     Order Discount.
     * @param creationDate Order Creation Date.
     * @param user         Order Customer.
     */
    public OrderTO(Long id, Double total, Double discount, Date creationDate, User user) {

        this.id = id;
        this.total = total;
        this.discount = discount;
        this.creationDate = creationDate;

        if (user != null)
            this.user = new UserTO(user);
    }

    /**
     * Entity Constructor.
     *
     * @param entity {@link Order} Entity.
     */
    public OrderTO(Order entity) {

        if (entity == null)
            return;

        this.id = entity.getId();
        this.total = entity.getTotal();
        this.creationDate = entity.getCreationDate();
        this.discount = entity.getDiscount();

        if (entity.getUser() != null)
            this.user = new UserTO(entity.getUser());

        List<OrderItem> items = entity.getItems();
        if (items != null && !items.isEmpty()) {
            this.items = new ArrayList<>();
            items.forEach((OrderItem i) -> this.items.add(new OrderItemTO(i)));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderTO that = (OrderTO) o;
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
