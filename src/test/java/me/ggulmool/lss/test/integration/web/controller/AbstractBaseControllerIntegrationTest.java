package me.ggulmool.lss.test.integration.web.controller;


import me.ggulmool.lss.test.integration.AbstractBaseIntegrationTest;
import me.ggulmool.lss.web.controller.RegistrationController;
import me.ggulmool.lss.web.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public abstract class AbstractBaseControllerIntegrationTest extends AbstractBaseIntegrationTest {

    @Autowired
    protected UserDetailsService userDetailsService;

    @Autowired
    private UserController userController;

    @Autowired
    private RegistrationController registrationController;

    protected MockMvc mockMvc;

    @Override
    public void setup() {
        super.setup();
        mockMvc = MockMvcBuilders.standaloneSetup(userController, registrationController).build();
    }

}
