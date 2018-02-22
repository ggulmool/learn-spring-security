package me.ggulmool.lss.service;

import javax.transaction.Transactional;

import me.ggulmool.lss.persistence.UserRepository;
import me.ggulmool.lss.validation.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.ggulmool.lss.web.model.User;

@Service
@Transactional
class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User registerNewUser(final User user) throws EmailExistsException {
        if (emailExist(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + user.getEmail());
        }
        return repository.save(user);
    }

    private boolean emailExist(String email) {
        final User user = repository.findByEmail(email);
        return user != null;
    }

}
