package com.system.online_exam_system.common.exceptions;

import org.springframework.http.HttpStatus;

public class StudentNotFound extends ApiException{
    public StudentNotFound() {
        super("Student not found", HttpStatus.NOT_FOUND);
    }
}
