package br.avcaliani.dxburgerapi.repository;

import br.avcaliani.dxburgerapi.domain.entity.Burger;
import br.avcaliani.dxburgerapi.domain.to.BurgerTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Burger Repository.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Repository
public interface BurgerRepository extends JpaRepository<Burger, Long> {

    /**
     * Find all burgers.
     *
     * @return {@link List} of {@link BurgerTO}.
     */
    @Query("SELECT new br.avcaliani.dxburgerapi.domain.to.BurgerTO(b) FROM Burger b")
    public List<BurgerTO> find();
}

