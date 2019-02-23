package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.Promotion;

import java.util.List;

/**
 * Promotion Service.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface PromotionService {

    /**
     * Return a list of Promotions based on active flag.
     *
     * @param active Active Flag.
     * @return {@link List} of {@link Promotion}.
     */
    public List<Promotion> find(boolean active);
}
