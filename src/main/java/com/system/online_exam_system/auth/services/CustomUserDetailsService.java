package com.system.online_exam_system.auth.services;

import com.system.online_exam_system.auth.entities.CustomUserDetails;
import com.system.online_exam_system.common.exceptions.UserNotFound;
import com.system.online_exam_system.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(UserNotFound::new);

        return new CustomUserDetails(user);
    }
}
