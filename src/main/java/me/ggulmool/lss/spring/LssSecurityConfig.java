package me.ggulmool.lss.spring;

import javax.annotation.PostConstruct;

import com.google.common.collect.Lists;
import me.ggulmool.lss.security.CustomAuthenticationProvider;
import me.ggulmool.lss.security.CustomAuthenticationProvider2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import me.ggulmool.lss.model.User;
import me.ggulmool.lss.persistence.UserRepository;

@EnableWebSecurity
public class LssSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomAuthenticationProvider2 customAuthenticationProvider2;

    public LssSecurityConfig() {
        super();
    }

    //

    @PostConstruct
    private void saveTestUser() {
        final User user = new User();
        user.setEmail("test@email.com");
        user.setPassword("pass");
        userRepository.save(user);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         /*final DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
         daoAuthProvider.setUserDetailsService(userDetailsService);
         auth.authenticationProvider(daoAuthProvider).authenticationProvider(customAuthenticationProvider);*/

        // auth.parentAuthenticationManager(new ProviderManager(Lists.newArrayList(customAuthenticationProvider)));

        ProviderManager authenticationManager = new ProviderManager(Lists.newArrayList(customAuthenticationProvider, customAuthenticationProvider2));
        authenticationManager.setEraseCredentialsAfterAuthentication(false);
        auth.parentAuthenticationManager(authenticationManager);
        //auth.eraseCredentials(false).userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
                .antMatchers("/badUser*","/js/**", "/h2-console").permitAll()
                .anyRequest().authenticated()

        .and()
        .formLogin().
            loginPage("/login").permitAll().
            loginProcessingUrl("/doLogin")

        .and()
        .logout().permitAll().logoutUrl("/logout")

        .and()
        .csrf().disable()
        .headers().frameOptions().disable();
    }

}
