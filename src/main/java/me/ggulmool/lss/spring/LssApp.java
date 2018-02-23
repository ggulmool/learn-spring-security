package me.ggulmool.lss.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"me.ggulmool.lss.web", "me.ggulmool.lss.persistence"})
@EnableJpaRepositories("me.ggulmool.lss")
@EntityScan("me.ggulmool.lss.persistence.model")
public class LssApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class[] { LssApp.class, CustomMethodSecurityConfig.class, LssSecurityConfig.class, LssWebMvcConfiguration.class }, args);
    }

}
