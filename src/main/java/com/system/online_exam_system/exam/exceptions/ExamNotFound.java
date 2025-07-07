package com.system.online_exam_system.exam.exceptions;

import com.system.online_exam_system.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class ExamNotFound extends ApiException {
    public ExamNotFound() {
        super("Exam not found", HttpStatus.NOT_FOUND);
    }
}
