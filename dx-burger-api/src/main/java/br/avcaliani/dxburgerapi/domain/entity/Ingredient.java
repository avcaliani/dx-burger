package br.avcaliani.dxburgerapi.domain.entity;

import br.avcaliani.dxburgerapi.domain.to.IngredientTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Ingredient Entity.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, columnDefinition = "DECIMAL(9,2)")
    private Double price;

    /**
     * Default Constructor.
     */
    public Ingredient() { }

    /**
     * TO Constructor.
     *
     * @param to {@link IngredientTO} TO.
     */
    public Ingredient(IngredientTO to) {

        if (to == null)
            return;

        this.id = to.getId();
        this.name = to.getName();
        this.price = to.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
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
