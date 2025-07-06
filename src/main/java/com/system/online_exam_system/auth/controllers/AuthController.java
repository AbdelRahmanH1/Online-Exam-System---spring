package com.system.online_exam_system.auth.controllers;

import com.system.online_exam_system.auth.dtos.LoginUserRequest;
import com.system.online_exam_system.auth.services.AuthService;
import com.system.online_exam_system.common.utils.ResponseUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserRequest request){

        var token = authService.login(request);
        return ResponseUtil.success(token);
    }
}
