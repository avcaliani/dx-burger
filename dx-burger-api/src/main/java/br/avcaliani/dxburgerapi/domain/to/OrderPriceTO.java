package br.avcaliani.dxburgerapi.domain.to;

import lombok.Data;

import java.util.List;

/**
 * Order Entity.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
public class OrderPriceTO {

    private Double total;
    private List<OrderItemTO> items;

    /**
     * Default Constructor.
     */
    public OrderPriceTO() { }

    /**
     * Full Constructor.
     *
     * @param total Order Total.
     * @param items Order Items.
     */
    public OrderPriceTO(Double total, List<OrderItemTO> items) {
        this.total = total;
        this.items = items;
    }
}
