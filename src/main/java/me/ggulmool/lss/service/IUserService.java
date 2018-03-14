package me.ggulmool.lss.service;

import me.ggulmool.lss.model.PasswordResetToken;
import me.ggulmool.lss.model.User;
import me.ggulmool.lss.validation.EmailExistsException;

public interface IUserService {

    User registerNewUser(User user) throws EmailExistsException;

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    PasswordResetToken getPasswordResetToken(String token);

    void changeUserPassword(User user, String password);

}
