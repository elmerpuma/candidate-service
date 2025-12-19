package com.company.clients.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final String defaultUsername;
    private final String defaultPasswordHash;

    public UserDetailsServiceImpl(
            @Value("${security.default-user.username:admin}") String defaultUsername,
            @Value("${security.default-user.password:password}") String defaultPassword
    ) {
        this.defaultUsername = defaultUsername;
        this.defaultPasswordHash = new BCryptPasswordEncoder().encode(defaultPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!defaultUsername.equals(username)) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return User.withUsername(defaultUsername)
                .password(defaultPasswordHash)
                .roles("ADMIN")
                .build();
    }
}

