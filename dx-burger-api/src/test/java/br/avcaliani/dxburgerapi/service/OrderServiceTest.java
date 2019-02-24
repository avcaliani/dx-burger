package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.to.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private OrderService service;

    @Autowired
    private IngredientService ingredientService;

    private OrderTO order;
    private OrderTO getOrder() {

        if (order != null)
            return order;

        this.order = new OrderTO();
        this.order.setUser(
                new UserTO("Anthony Caliani", "(19) 981.600.045")
        );

        BurgerTO burger = new BurgerTO();
        burger.setId(1l);

        OrderItemTO item = new OrderItemTO();
        item.setBurger(burger);
        item.setIngredients(new ArrayList<>());

        List<OrderItemTO> items = new ArrayList<>();
        items.add(item);
        this.order.setItems(items);

        return this.order;
    }

    @Test
    public void orderPriceTest() {

        OrderTO order = this.getOrder();
        List<OrderIngredientTO> ingredients = order.getItems().get(0).getIngredients();

        ingredients.add(new OrderIngredientTO(1, new IngredientTO(1l)));
        ingredients.add(new OrderIngredientTO(1, new IngredientTO(2l)));
        ingredients.add(new OrderIngredientTO(1, new IngredientTO(3l)));
        ingredients.add(new OrderIngredientTO(1, new IngredientTO(4l)));
        ingredients.add(new OrderIngredientTO(1, new IngredientTO(5l)));

        Double total = 0.0;
        total += this.ingredientService.getPrice(1l);
        total += this.ingredientService.getPrice(2l);
        total += this.ingredientService.getPrice(3l);
        total += this.ingredientService.getPrice(4l);
        total += this.ingredientService.getPrice(5l);

        try {
            Assert.assertEquals(
                    total, this.service.save(order).getTotal()
            );
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
        this.order = null;
    }

    @Test
    public void orderPromotionLightTest() {

        OrderTO order = this.getOrder();
        List<OrderIngredientTO> ingredients = order.getItems().get(0).getIngredients();

        ingredients.add(new OrderIngredientTO(1, new IngredientTO(1l)));

        Double ingPrice = this.ingredientService.getPrice(1l);
        try {
            Double orderTotal = this.service.save(order).getTotal();
            Double total =  (ingPrice - (ingPrice * 0.1));
            Assert.assertEquals(total, orderTotal);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void orderPromotionHamburgerTest() {

        OrderTO order = this.getOrder();
        List<OrderIngredientTO> ingredients = order.getItems().get(0).getIngredients();

        ingredients.add(new OrderIngredientTO(3, new IngredientTO(3l)));

        Double total = this.ingredientService.getPrice(3l) * 2;
        try {
            Double orderTotal = this.service.save(order).getTotal();
            Assert.assertEquals(total, orderTotal);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void orderPromotionCheeseTest() {

        OrderTO order = this.getOrder();
        List<OrderIngredientTO> ingredients = order.getItems().get(0).getIngredients();

        ingredients.add(new OrderIngredientTO(3, new IngredientTO(5l)));

        Double total = this.ingredientService.getPrice(5l) * 2;
        try {
            Double orderTotal = this.service.save(order).getTotal();
            Assert.assertEquals(total, orderTotal);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
