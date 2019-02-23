package br.avcaliani.dxburgerapi.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Burger Entity.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
@Entity
public class Burger {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "burger", cascade = CascadeType.ALL)
    private List<BurgerIngredient> ingredients;

    /**
     * Default Constructor.
     */
    public Burger() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Burger that = (Burger) o;
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
