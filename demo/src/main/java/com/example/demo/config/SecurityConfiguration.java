package com.example.demo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//References
///
//hhttps://www.marcobehler.com/guides/spring-securityttps://betterjavacode.com/programming/spring-security-filter-chain
//https://stackoverflow.com/questions/72381114/spring-security-upgrading-the-deprecated-websecurityconfigureradapter-in-spring
//https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
//https://www.youtube.com/watch?v=F31lvNRil10
//https://stackoverflow.com/questions/73322338/spring-security-5-7-multiple-authentication-provider-without-websecurityconfig
//https://reflectoring.io/spring-security-password-handling/
//https://stackoverflow.com/questions/71281032/spring-security-exposing-authenticationmanager-without-websecurityconfigureradap
//jwt: https://www.youtube.com/watch?v=TOQjvskdl3g&t=2340s
//jwt token filter : https://www.youtube.com/watch?v=1Mn1AFs8eDo&t=307s
//jwt token: https://www.youtube.com/watch?v=_L46CaEI490
//jwt: https://www.youtube.com/watch?v=KxqlJblhzfI

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired
    private CustomUserDetailService customUserDetailService;

  @Autowired
  private JWTTokenFilter jwtTokenFilter;
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

  @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/swagger-resources/**","/swagger-ui/**","/User/CreateUser","/Forgot-Password/**","/User/auth/Login","/v2/api-docs","/User/validateToken").permitAll().anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(authenticationProvider());
        return http.build();
    }

/* @Bean
    public InMemoryUserDetailsManager userDetailsService()
    {
        UserDetails user = User.withDefaultPasswordEncoder().username("sansa").password("12345").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }*/

}

