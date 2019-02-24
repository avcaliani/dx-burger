package br.avcaliani.dxburgerapi.service.impl;

import br.avcaliani.dxburgerapi.common.Visitor;
import br.avcaliani.dxburgerapi.domain.entity.*;
import br.avcaliani.dxburgerapi.repository.PromotionRepository;
import br.avcaliani.dxburgerapi.service.IngredientService;
import br.avcaliani.dxburgerapi.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Promotion Service Implementation.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository repository;

    @Autowired
    private IngredientService ingredientService;

    /**
     * @see IngredientService#find()
     */
    @Override
    public List<Promotion> find(boolean active) {
        return repository.findByActive(active);
    }

    /**
     * @see Visitor#visit(Order)
     */
    @Override
    public void visit(Order order) {

        if (order == null)
            return;

        List<OrderItem> orderItems = order.getItems();
        if (orderItems == null || orderItems.isEmpty())
            return;

        double discount = 0.0;
        double itemDiscount;
        List<Promotion> promotions = this.find(true);
        for (OrderItem item : orderItems) {
            itemDiscount = 0.0;
            for (Promotion promotion : promotions)
                itemDiscount += getDiscount(promotion, item);
            item.setDiscount(itemDiscount);
            item.setPrice(item.getPrice() - itemDiscount);
            discount += itemDiscount;
        }

        order.setDiscount(discount);
        order.setTotal(order.getTotal() - discount);
    }

    /**
     * Return discount value.
     *
     * @param promotion {@link Promotion}.
     * @param orderItem {@link OrderItem}.
     * @return Discount Value.
     */
    private Double getDiscount(Promotion promotion, OrderItem orderItem) {

        if (promotion == null || orderItem == null)
            return 0.0;

        Ingredient ingredient = promotion.getIngredient();
        List<OrderIngredient> orderIngredients = orderItem.getIngredients();
        if (orderIngredients == null || orderIngredients.isEmpty())
            return 0.0;

        if (ingredient == null && promotion.getPercent() != null)
            return percentage(promotion, orderItem);

        if (ingredient != null)
            return portion(promotion, orderItem);

        return 0.0;
    }

    /**
     * Calculate discount as percentage.
     *
     * @param promotion {@link Promotion}.
     * @param orderItem {@link OrderItem}.
     * @return Discount.
     */
    private Double percentage(Promotion promotion, OrderItem orderItem) {
        boolean present = contains(promotion.getPresent(), orderItem.getIngredients(), true);
        boolean notPresent = !contains(promotion.getNotPresent(), orderItem.getIngredients(), false);
        if (!present || !notPresent)
            return 0.0;
        return orderItem.getPrice() * promotion.getPercent();
    }

    /**
     * Calculate discount as portion.
     *
     * @param promotion {@link Promotion}.
     * @param orderItem {@link OrderItem}.
     * @return Discount.
     */
    private Double portion(Promotion promotion, OrderItem orderItem) {

        Ingredient ingredient = promotion.getIngredient();
        for (OrderIngredient oi : orderItem.getIngredients()) {

            if (oi == null || oi.getIngredient() == null || !ingredient.equals(oi.getIngredient()))
                continue;

            int quantity = oi.getQuantity() / promotion.getDivider();
            if (quantity < 1) return 0.0;
            return quantity * this.ingredientService.getPrice(
                    oi.getIngredient() != null ? oi.getIngredient().getId() : null
            );
        }
        return 0.0;
    }

    /**
     * Check if any ingredient is in/out of the Order Ingredient List.
     *
     * @param ingredients      Ingredients.
     * @param orderIngredients Order Ingredients.
     * @param exist            Flag.
     * @return True or False.
     */
    private boolean contains(List<Ingredient> ingredients, List<OrderIngredient> orderIngredients, boolean exist) {

        if (ingredients == null || ingredients.isEmpty())
            return exist;

        if (orderIngredients == null || orderIngredients.isEmpty())
            return false;

        int contain = 0;
        for (OrderIngredient oi : orderIngredients)
            if (ingredients.contains(oi.getIngredient()))
                contain++;

        return exist ? contain >= ingredients.size() : contain > 0;
    }
}
