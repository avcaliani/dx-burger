package br.avcaliani.dxburgerapi.service.impl;

import br.avcaliani.dxburgerapi.domain.entity.Order;
import br.avcaliani.dxburgerapi.domain.entity.OrderIngredient;
import br.avcaliani.dxburgerapi.domain.entity.OrderItem;
import br.avcaliani.dxburgerapi.domain.entity.User;
import br.avcaliani.dxburgerapi.domain.to.OrderTO;
import br.avcaliani.dxburgerapi.repository.OrderRepository;
import br.avcaliani.dxburgerapi.service.IngredientService;
import br.avcaliani.dxburgerapi.service.OrderService;
import br.avcaliani.dxburgerapi.service.PromotionService;
import br.avcaliani.dxburgerapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Order Service Implementation.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger L = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private PromotionService promotionService;

    /**
     * @see OrderService#find()
     */
    @Override
    public List<OrderTO> find() {
        return this.repository.find();
    }

    /**
     * @see OrderService#find(Long)
     */
    @Override
    public OrderTO find(Long id) {
        return this.repository.find(id);
    }

    /**
     * @see OrderService#save(OrderTO)
     */
    public OrderTO save(OrderTO order) throws Exception {

        Order entity = new Order(order);
        this.validate(entity);

        entity.setCreationDate(new Date());
        entity.accept(this);
        entity.accept(this.promotionService);
        this.fill(entity);

        try {
            return new OrderTO(this.repository.save(entity));
        } catch (Exception ex) {
            L.error("ERROR: Fail to Save Order.", ex);
            throw new Exception("Your order is not ok, check it please.");
        }
    }

    /**
     * @see OrderService#calculate(OrderTO)
     */
    @Override
    public OrderTO calculate(OrderTO order) {
        Order entity = new Order(order);
        entity.accept(this);
        entity.accept(this.promotionService);
        return new OrderTO(entity);
    }

    @Override
    public void visit(Order order) {

        if (order == null)
            return;

        List<OrderItem> orderItems = order.getItems();
        if (orderItems == null || orderItems.isEmpty())
            return;

        order.setTotal(this.getOrderPrice(orderItems));
    }

    /**
     * Calculate Order price.
     *
     * @param items Order Items.
     * @return Price.
     */
    private double getOrderPrice(List<OrderItem> items) {

        if (items == null || items.isEmpty())
            return 0.0;

        double total = 0.0;
        for (OrderItem item : items) {
            item.setPrice(this.getItemPrice(item.getIngredients()));
            total += item.getPrice();
        }
        return total;
    }

    /**
     * Calculate an Order Item price.
     *
     * @param ingredients Ingredients List.
     * @return Price.
     */
    private Double getItemPrice(List<OrderIngredient> ingredients) {

        if (ingredients == null || ingredients.isEmpty())
            return 0.0;

        Double price = 0.0;
        for (OrderIngredient oi : ingredients) {
            if (oi == null || oi.getQuantity() <= 0) continue;
            price += oi.getQuantity() * this.ingredientService.getPrice(
                    oi.getIngredient() != null ? oi.getIngredient().getId() : null
            );
        }
        return price;
    }

    /**
     * Check if Order data is right.
     *
     * @param order {@link Order}.
     * @throws Exception If something is not right.
     */
    private void validate(final Order order) throws Exception {

        if (order.getUser() == null)
            throw new Exception("User is required.");

        User user = this.userService.find(order.getUser().getPhone());
        if (user == null)
            user = this.userService.save(order.getUser());

        order.setUser(user);

        List<OrderItem> items = order.getItems();
        if (items == null || items.isEmpty())
            throw new Exception("At least one item is necessary to set up a new order.");

        boolean hasIngredient;
        List<OrderIngredient> ingredients;
        for (OrderItem item : items) {

            item.setOrder(order);
            ingredients = item.getIngredients();
            if (ingredients == null || ingredients.isEmpty())
                throw new Exception("At least one ingredient is necessary to define a burger.");

            hasIngredient = false;
            for (OrderIngredient ingredient : ingredients) {
                ingredient.setItem(item);
                if (ingredient.getQuantity() > 0) {
                    hasIngredient = true;
                    break;
                }
            }

            if (!hasIngredient)
                throw new Exception("At least one ingredient is necessary to define a burger.");
        }
    }

    /**
     * Make the relation between order items.
     *
     * @param order {@link Order}.
     */
    private void fill(Order order) {

        List<OrderItem> items = order.getItems();
        List<OrderIngredient> ingredients = null;
        for (OrderItem item : items) {
            item.setOrder(order);
            ingredients = item.getIngredients();
            for (OrderIngredient ingredient : ingredients)
                ingredient.setItem(item);
        }
    }
}
