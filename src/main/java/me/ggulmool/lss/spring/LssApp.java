package me.ggulmool.lss.spring;

import me.ggulmool.lss.persistence.InMemoryUserRepository;
import me.ggulmool.lss.persistence.UserRepository;
import me.ggulmool.lss.web.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;

@SpringBootApplication
@ComponentScan("me.ggulmool.lss.web")
public class LssApp {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public Converter<String, User> messageConverter() {
        return new Converter<String, User>() {
            @Override
            public User convert(String id) {
                return userRepository().findUser(Long.valueOf(id));
            }
        };
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class[]{LssApp.class, LssSecurityConfig.class}, args);
    }

}
