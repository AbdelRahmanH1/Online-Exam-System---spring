package com.system.online_exam_system.auth.dtos;

import com.system.online_exam_system.common.exceptions.ApiException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class LoginUserRequest {


    @NotBlank(message = "username is required")
    private String username;
    @NotBlank
    @Size(min = 6,message = "Password at least 6 characters")
    private String password;

    public void validateUsernameFormat() {
        String pattern = "^[a-z]+_[A-Z]_[a-z]+_\\d{4}$";

        if (!username.matches(pattern)) {
            throw new ApiException("Invalid username format", HttpStatus.BAD_REQUEST);
        }
    }
}
