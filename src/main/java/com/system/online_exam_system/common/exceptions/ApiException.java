package com.system.online_exam_system.common.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException{
    private final HttpStatus status;
    public ApiException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
