package com.system.online_exam_system.user.controllers;

import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.user.dtos.CreateUserRequest;
import com.system.online_exam_system.user.services.AdminUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final AdminUserService adminUserService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request){
        var response = adminUserService.createUser(request);
        return ResponseUtil.success("User Created successfully",response, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers(@RequestParam(name = "page",required = false) Integer page){
        var response = adminUserService.getAllUsers(page);
        return ResponseUtil.success("All Users Found",response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id){
        var response = adminUserService.getUserById(id);
        return ResponseUtil.success("User Found",response);
    }
}
