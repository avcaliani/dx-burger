package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.User;
import br.avcaliani.dxburgerapi.repository.UserRepository;
import br.avcaliani.dxburgerapi.util.Parser;
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
        return repository.findByPhone(Parser.replaceAllNonNumeric(phone));
    }

    /**
     * @see UserService#save(User)
     */
    @Override
    public User save(User user) throws Exception {

        if (user == null)
            return null;

        this.validate(user);
        user.setPhone(Parser.replaceAllNonNumeric(user.getPhone()));

        return repository.save(user);
    }

    /**
     * Check if User data is right.
     *
     * @param user {@link User}.
     * @throws Exception If something is not right.
     */
    private void validate(User user) throws Exception {
        if (user == null)
            throw new Exception("User is required.");
        if (user.getName() == null || user.getName().trim().isEmpty())
            throw new Exception("User 'name' is required.");
        if (user.getPhone() == null || user.getPhone().trim().isEmpty())
            throw new Exception("User 'phone' is required.");
    }
}
