package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.Order;
import br.avcaliani.dxburgerapi.domain.to.OrderTO;
import br.avcaliani.dxburgerapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Order Service Implementation.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    /**
     * @see OrderService#find()
     */
    @Override
    public List<OrderTO> find() {
        return null; // TODO: Do it ;)
    }

    /**
     * @see OrderService#find(Long)
     */
    @Override
    public OrderTO find(Long id) {
        return null; // TODO: Do it ;)
    }

    /**
     * @see OrderService#save(OrderTO)
     */
    public OrderTO save(OrderTO order){
        // TODO: Do it ;)
        return null;
    }
}
