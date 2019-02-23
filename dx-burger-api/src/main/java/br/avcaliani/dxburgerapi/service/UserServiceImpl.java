package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.User;
import br.avcaliani.dxburgerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User Service Implementation.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    /**
     * @see UserService#find(String)
     */
    @Override
    public User find(String phone) {
        if (phone == null || phone.trim().isEmpty())
            return null;
        return repository.findByPhone(phone);
    }
}
