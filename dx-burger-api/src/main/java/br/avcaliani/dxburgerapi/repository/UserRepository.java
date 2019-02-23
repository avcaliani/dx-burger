package br.avcaliani.dxburgerapi.repository;

import br.avcaliani.dxburgerapi.domain.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Ingredient Repository.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface PromotionRepository extends JpaRepository<Promotion, Long> {  }
