package br.avcaliani.dxburgerapi.common;

import br.avcaliani.dxburgerapi.domain.entity.Order;

/**
 * Visitor Object Interface.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface Visitor {

    /**
     * Visit Order.
     *
     * @param order {@link Order}.
     */
    public void visit(Order order);
}
