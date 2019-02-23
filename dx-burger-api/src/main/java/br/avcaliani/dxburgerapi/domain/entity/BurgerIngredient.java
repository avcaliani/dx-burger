package br.avcaliani.dxburgerapi.domain.entity;

import br.avcaliani.dxburgerapi.domain.to.BurgerIngredientTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * Burger Ingredient Entity.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
@Entity
public class BurgerIngredient {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "burger_id")
    private Burger burger;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    /**
     * Default Constructor.
     */
    public BurgerIngredient() { }

    /**
     * Basic Constructor.
     *
     * @param quantity Ingredient Quantity.
     * @param ingredient {@link Ingredient}.
     */
    public BurgerIngredient(Integer quantity, Ingredient ingredient) {
        this.quantity = quantity;
        this.ingredient = ingredient;
    }

    /**
     * TO Constructor.
     *
     * @param to {@link BurgerIngredientTO} TO.
     */
    public BurgerIngredient(BurgerIngredientTO to) {

        if (to == null)
            return;

        this.quantity = to.getQuantity();
        if (to.getIngredient() != null)
            this.ingredient = new Ingredient(to.getIngredient());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BurgerIngredient that = (BurgerIngredient) o;
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
