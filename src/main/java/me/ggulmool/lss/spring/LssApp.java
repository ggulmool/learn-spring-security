package me.ggulmool.lss.spring;

import me.ggulmool.lss.persistence.InMemoryUserRepository;
import me.ggulmool.lss.persistence.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("me.ggulmool.lss.web")
public class LssApp {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class[] {LssApp.class, LssSecurityConfig.class, LssWebMvcConfiguration.class }, args);
    }

}
