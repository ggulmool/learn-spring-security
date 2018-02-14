package me.ggulmool.lss.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("me.ggulmool.lss")
@EnableJpaRepositories("me.ggulmool.lss")
@EntityScan("me.ggulmool.lss.web.model")
public class LssApp extends SpringBootServletInitializer {
	
	private final static Class<?>[] CONFIGS = {
			LssSecurityConfig.class,
			LssWebMvcConfiguration.class,           
			LssApp.class
    };

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CONFIGS);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CONFIGS,args);
	}

}
