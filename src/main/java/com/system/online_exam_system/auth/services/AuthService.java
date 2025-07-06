package com.system.online_exam_system.auth.services;

import com.system.online_exam_system.auth.dtos.JwtResponse;
import com.system.online_exam_system.auth.dtos.LoginUserRequest;
import com.system.online_exam_system.auth.entities.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtResponse login(LoginUserRequest request) {
        request.validateUsernameFormat();

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = (CustomUserDetails) authentication.getPrincipal();
        var token = jwtService.generateToken(user);

        return new JwtResponse(token);

    }
}
