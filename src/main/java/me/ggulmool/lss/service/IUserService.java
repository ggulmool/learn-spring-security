package me.ggulmool.lss.service;

import me.ggulmool.lss.model.VerificationToken;
import me.ggulmool.lss.validation.EmailExistsException;
import me.ggulmool.lss.model.User;

public interface IUserService {

    User findUserByEmail(final String email);

    User registerNewUser(User user) throws EmailExistsException;

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String token);

    void saveRegisteredUser(User user);
}
