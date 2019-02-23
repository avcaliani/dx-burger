package br.avcaliani.dxburgerapi.domain.to;

import br.avcaliani.dxburgerapi.domain.entity.User;
import lombok.Data;

import java.util.Objects;

/**
 * User Entity.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
public class UserTO {

    private Long id;
    private String name;
    private String phone;

    /**
     * Default Constructor.
     */
    public UserTO() { }

    /**
     * Entity Constructor.
     *
     * @param entity {@link User} Entity.
     */
    public UserTO(User entity) {

        if (entity == null)
            return;

        this.id = entity.getId();
        this.name = entity.getName();
        this.phone = entity.getPhone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTO that = (UserTO) o;
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
