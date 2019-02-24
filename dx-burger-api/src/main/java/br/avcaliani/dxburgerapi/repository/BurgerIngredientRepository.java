package br.avcaliani.dxburgerapi.repository;

import br.avcaliani.dxburgerapi.domain.entity.BurgerIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Burger Repository.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface BurgerIngredientRepository extends JpaRepository<BurgerIngredient, Long> {

    /**
     * Return Burger Price.
     *
     * @param burgerId Burger Id.
     * @return Burger Price.
     */
    @Query("SELECT SUM(bi.quantity * bi.ingredient.price) FROM BurgerIngredient bi WHERE bi.burger.id = :burgerId")
    public Double getBurgerPrice(@Param("burgerId") Long burgerId);
}

