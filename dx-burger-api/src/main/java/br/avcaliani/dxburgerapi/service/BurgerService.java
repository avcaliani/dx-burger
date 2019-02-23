package br.avcaliani.dxburgerapi.repository;

import br.avcaliani.dxburgerapi.domain.entity.Burger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Burger Repository.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface BurgerRepository extends JpaRepository<Burger, Long> {  }
