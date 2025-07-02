package com.system.online_exam_system.user.dtos;

import com.system.online_exam_system.user.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 6,message = "Password at least 6 characters")
    private String password;

    @NotNull
    private Role role;

    @Min(1)
    @Max(12)
    private Integer grade;

    private String department;
}
