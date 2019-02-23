package br.avcaliani.dxburgerapi.repository;

import br.avcaliani.dxburgerapi.domain.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Ingredient Repository.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {  }
