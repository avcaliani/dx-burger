package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.Burger;
import br.avcaliani.dxburgerapi.domain.to.BurgerTO;
import br.avcaliani.dxburgerapi.repository.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Burger Service Implementation.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Service
public class BurgerServiceImpl implements BurgerService {

    @Autowired
    private BurgerRepository repository;

    /**
     * @see BurgerService#find()
     */
    @Override
    public List<BurgerTO> find() {
        return parse(repository.findAll());
    }

    /**
     * @see BurgerService#find(Long)
     */
    @Override
    public BurgerTO find(Long id) {

        if (id == null || id < 0)
            return null;

        Optional<Burger> burger = repository.findById(id);
        if (burger.isPresent())
            return new BurgerTO(burger.get());
        return null;
    }

    /**
     * Return a list of {@link BurgerTO} based on a list of {@link Burger}.
     *
     * @param burgers List of {@link Burger}.
     * @return List of {@link BurgerTO}.
     */
    private List<BurgerTO> parse(List<Burger> burgers) {
        if (burgers == null || burgers.isEmpty())
            return null;
        List<BurgerTO> tos = new ArrayList<>();
        burgers.forEach((Burger b) -> tos.add(new BurgerTO(b)));
        return tos;
    }
}
