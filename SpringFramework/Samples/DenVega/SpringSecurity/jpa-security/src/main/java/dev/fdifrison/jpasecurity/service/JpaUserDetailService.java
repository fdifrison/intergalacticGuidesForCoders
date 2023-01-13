package dev.fdifrison.jpasecurity.service;

import dev.fdifrison.jpasecurity.model.SecurityUser;
import dev.fdifrison.jpasecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // we could hard code a user or one we have on our db
        return userRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                // if the username is found in the DB, then map it to the Security user or else throw an exception
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}
