package me.ggulmool.lss.service;

import javax.transaction.Transactional;
import java.util.Optional;

import me.ggulmool.lss.model.PasswordResetToken;
import me.ggulmool.lss.model.User;
import me.ggulmool.lss.model.VerificationToken;
import me.ggulmool.lss.persistence.PasswordResetTokenRepository;
import me.ggulmool.lss.persistence.UserRepository;
import me.ggulmool.lss.persistence.VerificationTokenRepository;
import me.ggulmool.lss.validation.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    @Override
    public User registerNewUser(final User user) throws EmailExistsException {
        checkForNull(user, "user");

        if (emailExist(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(final String email) {
        checkForNull(email, "email");

        return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        checkForNull(user, "user");
        checkForNull(user.getEmail(), "user.email");
        checkForNull(token, "token");

        final User persistedUser = getGetUserByEmail(user.getEmail());
        final PasswordResetToken myToken = new PasswordResetToken(token, persistedUser);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        checkForNull(token, "token");

        return passwordTokenRepository.findByToken(token);
    }

    @Override
    public void changeUserPassword(final User user, final String password) {
        checkForNull(user, "user");
        checkForNull(user.getEmail(), "user.email");
        checkForNull(password, "password");

        final User persistedUser = getGetUserByEmail(user.getEmail());
        persistedUser.setPassword(password);
        userRepository.save(persistedUser);
    }

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
        checkForNull(user, "user");
        checkForNull(user.getEmail(), "user.email");
        checkForNull(token, "token");

        final User persistedUser = getGetUserByEmail(user.getEmail());
        final VerificationToken myToken = new VerificationToken(token, persistedUser);
        verificationTokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(final String token) {
        checkForNull(token, "token");

        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void saveRegisteredUser(final User user) {
        checkForNull(user, "user");
        checkForNull(user.getId(), "user.id");

        final User persistedUser = getGetUserById(user.getId());
        user.setCreated(persistedUser.getCreated());
        userRepository.save(user);
    }

    // Private Helper Methods

    private User getGetUserById(final Long id) {
        return Optional.ofNullable(userRepository.findOne(id))
                .orElseThrow(() -> new IllegalArgumentException(String.format("user with id=\"%s\" does not exist", id)));
    }

    private User getGetUserByEmail(final String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new IllegalArgumentException(String.format("user with email=\"%s\" does not exist", email)));
    }

    private boolean emailExist(final String email) {
        final User user = userRepository.findByEmail(email);
        return user != null;
    }

    private void checkForNull(final Object object, final String objectName) {
        if (object == null) {
            throw new IllegalArgumentException(objectName + " cannot be null");
        }
    }

}
