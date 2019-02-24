package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.Burger;
import br.avcaliani.dxburgerapi.domain.entity.BurgerIngredient;
import br.avcaliani.dxburgerapi.domain.entity.Ingredient;
import br.avcaliani.dxburgerapi.domain.to.BurgerIngredientTO;
import br.avcaliani.dxburgerapi.domain.to.BurgerTO;
import br.avcaliani.dxburgerapi.domain.to.IngredientTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BurgerServiceTest {

    private static final Logger L = LoggerFactory.getLogger(BurgerServiceTest.class);

    @Autowired
    private BurgerService service;

    @Autowired
    private IngredientService ingredientService;

    @Test
    public void burgerPriceTest() {

        List<BurgerTO> burgers = this.service.find();
        for (BurgerTO burger : burgers) {

            Double price = burger.getPrice();
            Double checkUp = 0.0;

            List<BurgerIngredientTO> bis = this.service.find(burger.getId()).getIngredients();
            for (BurgerIngredientTO bi : bis)
                checkUp += bi.getQuantity() * this.ingredientService.getPrice(bi.getIngredient().getId());

            if (L.isDebugEnabled())
                L.debug(String.format(
                        "Burger Id: %d | Price: %.2f | Check Up: %.2f", burger.getId(), price, checkUp
                ));

            Assert.assertEquals(checkUp, price);
        }
    }
}
