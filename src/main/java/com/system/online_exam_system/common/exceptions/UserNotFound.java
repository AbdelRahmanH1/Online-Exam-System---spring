package com.system.online_exam_system.common.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFound extends ApiException{
    public UserNotFound() {
        super("User not found",HttpStatus.NOT_FOUND);
    }
}
