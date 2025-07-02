package com.system.online_exam_system.user.controllers;

import com.system.online_exam_system.user.dtos.CreateUserRequest;
import com.system.online_exam_system.user.services.AdminUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final AdminUserService adminUserService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request){
        var response = adminUserService.createUser(request);
        return ResponseEntity.ok(response);
    }
}
