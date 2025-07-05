package com.system.online_exam_system.common.exceptions;

import org.springframework.http.HttpStatus;

public class InstructorNotFound extends ApiException {
    public InstructorNotFound() {
        super("Instructor not found", HttpStatus.NOT_FOUND);
    }
}
