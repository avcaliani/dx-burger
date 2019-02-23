package br.avcaliani.dxburgerapi.repository;

import br.avcaliani.dxburgerapi.domain.entity.Ingredient;
import br.avcaliani.dxburgerapi.domain.to.IngredientTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Ingredient Repository.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
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
    @Query("SELECT i FROM Ingredient i WHERE i.name NOT IN :names")
    public List<Ingredient> findMissingIngredients(@Param("names") List<String> names);
}
