package com.system.online_exam_system.auth.controllers;

import com.system.online_exam_system.auth.dtos.LoginUserRequest;
import com.system.online_exam_system.auth.services.AuthService;
import com.system.online_exam_system.common.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication", description = "Public login endpoint for users")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(
            summary = "Login and get JWT token",
            description = "Authenticate a user with username and password. Returns a JWT token if successful."
    )
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserRequest request){

        var token = authService.login(request);
        return ResponseUtil.success(token);
    }
}
