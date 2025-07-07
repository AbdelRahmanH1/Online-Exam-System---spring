package com.system.online_exam_system.exam.exceptions;

import com.system.online_exam_system.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class InvalidTimeException extends ApiException {
    public InvalidTimeException() {
        super("Time not valid", HttpStatus.BAD_REQUEST);
    }
}
