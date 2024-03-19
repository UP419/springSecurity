package com.example.springSecurity.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class UserDao {

    private final static List<UserDetails> APPLICATION_USERS =
            Arrays.asList(
                    new User(
                            "my.mail@gmail.com",
                            "password",
                            Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
                    ),
                    new User(
                            "other.mail@gmail.com",
                            "password123",
                            Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
                    )
            );

    public UserDetails getUserByEmail(String email) {
        return APPLICATION_USERS.stream()
                .filter(user -> user.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

}
