package com.example.demo.config;
import com.example.demo.controller.UserRegistrationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//https://betterjavacode.com/programming/spring-security-filter-chain
//https://stackoverflow.com/questions/72381114/spring-security-upgrading-the-deprecated-websecurityconfigureradapter-in-spring
//https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserRegistrationController userRegistrationController;

    @Bean
    public InMemoryUserDetailsManager userDetailsService()
    {

        String userName = userRegistrationController.getUserWithUserName("sansa").getUserName();
        String password = userRegistrationController.getUserWithUserName("sansa").getPassword();
        UserDetails user = User.withDefaultPasswordEncoder().username(userName).password(password).roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }

@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().antMatchers("/").hasRole("USER").anyRequest().authenticated().and().httpBasic()
                .and().build();
    }

}
