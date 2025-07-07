package com.system.online_exam_system.auth.services;

import com.system.online_exam_system.auth.entities.CustomUserDetails;
import com.system.online_exam_system.common.exceptions.UserNotFound;
import com.system.online_exam_system.user.mappers.UserMapper;
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
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var userProjection = userRepository.findBaseUserOnly(username).orElseThrow(UserNotFound::new);
        var user = userMapper.toEntity(userProjection);
        return new CustomUserDetails(user);
    }
}
