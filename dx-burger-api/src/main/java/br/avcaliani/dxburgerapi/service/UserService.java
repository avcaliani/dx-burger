package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.User;

/**
 * User Service.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public interface UserService {

    /**
     * Return user based on its phone number.
     *
     * @param phone User Phone Number.
     * @return {@link User}.
     */
    public User find(String phone);

    /**
     * Save a new User.
     *
     * @param user User Phone Number.
     * @return {@link User}.
     */
    public User save(User user) throws Exception;
}
