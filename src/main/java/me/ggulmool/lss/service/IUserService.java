package me.ggulmool.lss.service;

import me.ggulmool.lss.model.PasswordResetToken;
import me.ggulmool.lss.model.VerificationToken;
import me.ggulmool.lss.validation.EmailExistsException;
import me.ggulmool.lss.model.User;

public interface IUserService {

    User registerNewUser(User user) throws EmailExistsException;

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    PasswordResetToken getPasswordResetToken(String token);

    void changeUserPassword(User user, String password);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String token);

    void saveRegisteredUser(User user);
}
