package br.avcaliani.dxburgerapi.repository;

import br.avcaliani.dxburgerapi.domain.entity.Ingredient;
import br.avcaliani.dxburgerapi.domain.to.IngredientTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Ingredient Repository.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    /**
     * Find all Ingredients.
     *
     * @return {@link List} of {@link IngredientTO}.
     */
    @Query("SELECT new br.avcaliani.dxburgerapi.domain.to.IngredientTO(i) FROM Ingredient i")
    public List<IngredientTO> find();

    /**
     * Find all Ingredients witch aren't in {@code names} list.
     *
     * @return {@link List} of {@link Ingredient}.
     */
    @Query("SELECT new br.avcaliani.dxburgerapi.domain.to.IngredientTO(i) FROM Ingredient i WHERE i.name NOT IN :names")
    public List<IngredientTO> findMissingIngredients(@Param("names") List<String> names);

    /**
     * Return ingredient price based on ingredient id.
     *
     * @return Ingredient Price.
     */
    @Query("SELECT i.price FROM Ingredient i WHERE i.id = :id")
    public Double getPrice(@Param("id") Long id);
}
