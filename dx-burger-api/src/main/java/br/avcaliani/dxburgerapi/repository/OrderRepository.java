package br.avcaliani.dxburgerapi.repository;

import br.avcaliani.dxburgerapi.domain.entity.Order;
import br.avcaliani.dxburgerapi.domain.to.OrderTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Order Repository.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find all orders.
     *
     * @return {@link List} of {@link OrderTO}.
     */
    @Query("SELECT new br.avcaliani.dxburgerapi.domain.to.OrderTO(o.id, o.total, o.discount, o.creationDate, o.user) FROM Order o")
    public List<OrderTO> find();

    /**
     * Find order by Id.
     *
     * @return {@link OrderTO}.
     */
    @Query("SELECT new br.avcaliani.dxburgerapi.domain.to.OrderTO(o) FROM Order o WHERE o.id = :id")
    public OrderTO find(@Param("id") Long id);
}
