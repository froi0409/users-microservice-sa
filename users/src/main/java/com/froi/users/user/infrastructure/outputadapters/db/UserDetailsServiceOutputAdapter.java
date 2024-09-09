package com.froi.users.user.infrastructure.outputadapters.db;

import com.froi.users.common.PersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@PersistenceAdapter
public class UserDetailsServiceOutputAdapter implements UserDetailsService {

    private UserDbEntityRepository userDbEntityRepository;

    @Autowired
    public UserDetailsServiceOutputAdapter(UserDbEntityRepository userDbEntityRepository) {
        this.userDbEntityRepository = userDbEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDbEntity userOpt = userDbEntityRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(userOpt.getUsername())
                .password(userOpt.getPassword())
                .roles(userOpt.getRole())
                .build();
    }
}
