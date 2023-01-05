
package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRegistrationRepository repository;

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User singleuser = repository.findByUserName(username);
         System.out.println("User in user details service is "+singleuser);
        if (singleuser == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user =  org.springframework.security.core.userdetails.User.withUsername(singleuser.getUserName()).password(singleuser.getPassword()).roles("USER").build();
        System.out.println("User in user details service is "+user);
        return user;
    }
}

