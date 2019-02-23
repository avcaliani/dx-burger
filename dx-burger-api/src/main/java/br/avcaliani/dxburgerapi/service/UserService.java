package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.to.BurgerTO;

import java.util.List;

/**
 * Burger Service.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface BurgerService {

    /**
     * Return a list of Burgers.
     *
     * @return {@link List} of {@link BurgerTO}.
     */
    public List<BurgerTO> find();

    /**
     * Return a Burger based on Burger Id.
     *
     * @param burgerId Burger Id.
     * @return {@link BurgerTO}.
     */
    public BurgerTO find(Long burgerId);
}
