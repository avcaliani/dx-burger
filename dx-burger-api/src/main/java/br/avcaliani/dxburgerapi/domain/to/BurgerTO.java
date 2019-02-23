package br.avcaliani.dxburgerapi.domain.to;

import br.avcaliani.dxburgerapi.domain.entity.Burger;
import br.avcaliani.dxburgerapi.domain.entity.BurgerIngredient;
import br.avcaliani.dxburgerapi.domain.entity.Ingredient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Burger Transfer Object.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
public class BurgerTO {

    private Long id;
    private String name;
    private List<BurgerIngredientTO> ingredients;

    /**
     * Default Constructor.
     */
    public BurgerTO() { }

    /**
     * Entity Constructor.
     *
     * @param entity {@link Burger} Entity.
     */
    public BurgerTO(Burger entity) {

        if (entity == null)
            return;

        this.id = entity.getId();
        this.name = entity.getName();

        List<BurgerIngredient> ingredients = entity.getIngredients();
        if (ingredients != null && !ingredients.isEmpty()) {
            this.ingredients = new ArrayList<>();
            ingredients.forEach(
                    (BurgerIngredient i) -> this.ingredients.add(new BurgerIngredientTO(i))
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BurgerTO that = (BurgerTO) o;
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
