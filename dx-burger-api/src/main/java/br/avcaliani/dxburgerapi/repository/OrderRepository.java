package br.avcaliani.dxburgerapi.repository;

import br.avcaliani.dxburgerapi.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Order Repository.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface OrderRepository extends JpaRepository<Order, Long> {  }
