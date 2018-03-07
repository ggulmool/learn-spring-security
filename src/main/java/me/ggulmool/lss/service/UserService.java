package me.ggulmool.lss.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.ggulmool.lss.model.User;
import me.ggulmool.lss.persistence.UserRepository;
import me.ggulmool.lss.validation.EmailExistsException;

@Service
@Transactional
class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerNewUser(final User user) throws EmailExistsException {
        if (emailExist(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    private boolean emailExist(final String email) {
        final User user = userRepository.findByEmail(email);
        return user != null;
    }

}
