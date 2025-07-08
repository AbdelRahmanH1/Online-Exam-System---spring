package com.system.online_exam_system.exam.exceptions;

import com.system.online_exam_system.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class GradeMismatchException extends ApiException {
    public GradeMismatchException() {
        super("This exam is not for your grade level.", HttpStatus.FORBIDDEN);
    }
}