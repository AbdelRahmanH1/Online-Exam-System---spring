package com.system.online_exam_system.user.dtos;

import com.system.online_exam_system.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private Role role;
}
