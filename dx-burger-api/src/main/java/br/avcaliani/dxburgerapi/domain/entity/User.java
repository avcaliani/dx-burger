package br.avcaliani.dxburgerapi.domain.entity;

import br.avcaliani.dxburgerapi.domain.to.UserTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * User Entity.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 20)
    private String phone;

    /**
     * Default Constructor.
     */
    public User() { }

    /**
     * TO Constructor.
     *
     * @param to {@link UserTO} TO.
     */
    public User(UserTO to) {

        if (to == null)
            return;

        this.id = to.getId();
        this.name = to.getName();
        this.phone = to.getPhone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
