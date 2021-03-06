package br.avcaliani.dxburgerapi.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Promotion Entity.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
@Entity
public class Promotion {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(columnDefinition = "DECIMAL(9,2)")
    private Double percent;

    private Integer divider;
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "promotion_ingredient_present",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> present;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "promotion_ingredient_not_present",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> notPresent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotion that = (Promotion) o;
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
