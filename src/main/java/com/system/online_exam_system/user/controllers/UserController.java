package com.system.online_exam_system.user.controllers;

import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.user.dtos.CreateUserRequest;
import com.system.online_exam_system.user.services.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
@Tag(name = "Users", description = "Admin-only endpoints for managing all user accounts")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final AdminUserService adminUserService;

    @PostMapping
    @Operation(summary = "Admin create a new user")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request){
        var response = adminUserService.createUser(request);
        return ResponseUtil.success("User Created successfully",response, HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "Get all users")
    public ResponseEntity<?> getAllUsers(@RequestParam(name = "page",required = false) Integer page){
        var response = adminUserService.getAllUsers(page);
        return ResponseUtil.success("All Users Found",response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id){
        var response = adminUserService.getUserById(id);
        return ResponseUtil.success("User Found",response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by ID")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "id") Long id){
        adminUserService.deleteUserById(id);
        return ResponseUtil.success("User deleted successfully",null);
    }
}
