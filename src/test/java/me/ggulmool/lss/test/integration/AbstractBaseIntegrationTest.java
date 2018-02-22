package me.ggulmool.lss.test.integration;

import com.icegreen.greenmail.util.DummySSLSocketFactory;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import me.ggulmool.lss.model.User;
import me.ggulmool.lss.persistence.PasswordResetTokenRepository;
import me.ggulmool.lss.persistence.UserRepository;
import me.ggulmool.lss.persistence.VerificationTokenRepository;
import me.ggulmool.lss.service.IUserService;
import me.ggulmool.lss.spring.LssApp;
import me.ggulmool.lss.validation.EmailExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.internet.MimeMessage;
import java.security.Security;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes= {LssApp.class})
@TestPropertySource(locations = "classpath:test.properties")
public abstract class AbstractBaseIntegrationTest {

    protected static final String VALUE_DEFAULT_USER_EMAIL = "integration.test@email.com";
    protected static final String VALUE_DEFAULT_USER_PASSWORD = "sadfbjH$1";

    // Services

    @Autowired
    protected IUserService userService;

    // Repositories

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected VerificationTokenRepository verificationTokenRepository;

    @Autowired
    protected PasswordResetTokenRepository passwordResetTokenRepository;

    // Email

    private GreenMail greenMail;

    // Tests

    @Test
    public void noInitializationErrors() {

    }

    //

    abstract protected Boolean startSmtpServer();

    @Before
    public void setup() {
        cleanDatabase();
        startSmtp();
    }

    @After
    public void tearDown() {
        cleanDatabase();
        stopSmpt();
    }

    protected MimeMessage getEmail(final Integer index) {
        return greenMail.getReceivedMessages()[index];
    }

    protected Integer getEmailsCount() {
        return greenMail.getReceivedMessages().length;
    }

    protected User registerNewUser() throws EmailExistsException {
        return registerNewUser(VALUE_DEFAULT_USER_EMAIL);
    }

    protected User registerNewUser(final String email) throws EmailExistsException {
        return userService.registerNewUser(createNewUser(email));
    }

    protected User createNewUser() {
        return createNewUser(VALUE_DEFAULT_USER_EMAIL);
    }

    protected User createNewUser(final String email) {
        final User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(VALUE_DEFAULT_USER_PASSWORD);
        return newUser;
    }

    // Private Helper Methods

    private void cleanDatabase() {
        passwordResetTokenRepository.deleteAll();
        verificationTokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    private void startSmtp() {
        if (startSmtpServer()) {
            Security.setProperty("ssl.SocketFactory.provider", DummySSLSocketFactory.class.getName());
            greenMail = new GreenMail(ServerSetupTest.SMTPS);
            greenMail.start();
        }
    }

    private void stopSmpt() {
        if (startSmtpServer()) {
            greenMail.stop();
        }
    }

}
