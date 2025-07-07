package com.system.online_exam_system.exam.exceptions;

import com.system.online_exam_system.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {
    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
