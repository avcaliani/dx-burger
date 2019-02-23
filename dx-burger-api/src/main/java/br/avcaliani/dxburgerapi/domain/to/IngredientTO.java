package br.avcaliani.dxburgerapi.domain.to;

import br.avcaliani.dxburgerapi.domain.entity.Ingredient;
import lombok.Data;

import java.util.Objects;

/**
 * Ingredient Transfer Object.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
public class IngredientTO {

    private Long id;
    private String name;
    private Double price;

    /**
     * Default Constructor.
     */
    public IngredientTO() { }

    /**
     * Entity Constructor.
     *
     * @param entity {@link Ingredient} Entity.
     */
    public IngredientTO(Ingredient entity) {

        if (entity == null)
            return;

        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientTO that = (IngredientTO) o;
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
