package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.common.Visitor;
import br.avcaliani.dxburgerapi.domain.to.OrderTO;

import java.util.List;

/**
 * Order Service.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface OrderService extends Visitor {

    /**
     * Return a list of Orders.
     *
     * @return {@link List} of {@link OrderTO}.
     */
    public List<OrderTO> find();

    /**
     * Return an Order based on Order Id.
     *
     * @param id Order Id.
     * @return {@link OrderTO}.
     */
    public OrderTO find(Long id);

    /**
     * Save a new Order.
     *
     * @param order {@link OrderTO}.
     * @return {@link OrderTO}.
     */
    public OrderTO save(OrderTO order) throws Exception;

    /**
     * Calculate Order and Order Items price.
     *
     * @param order Order.
     * @return {@link OrderTO}.
     */
    public OrderTO calculate(OrderTO order);
}
