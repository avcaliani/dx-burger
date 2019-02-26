package br.avcaliani.dxburgerapi.repository;

import br.avcaliani.dxburgerapi.domain.entity.Burger;
import br.avcaliani.dxburgerapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User Repository.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find all burgers.
     *
     * @return {@link List} of {@link Burger}.
     */
    @Query("SELECT u FROM User u WHERE u.phone = :phone")
    public User findByPhone(@Param("phone") String phone);
}
