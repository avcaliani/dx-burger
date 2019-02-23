package br.avcaliani.dxburgerapi.repository;

import br.avcaliani.dxburgerapi.domain.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Promotion Repository.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    /**
     * Return a list of Promotions based on active flag.
     *
     * @param active Active Flag.
     * @return {@link List} of {@link Promotion}.
     */
    @Query("SELECT p FROM Promotion p WHERE p.active = :active")
    public List<Promotion> findByActive(@Param("active") Boolean active);
}
