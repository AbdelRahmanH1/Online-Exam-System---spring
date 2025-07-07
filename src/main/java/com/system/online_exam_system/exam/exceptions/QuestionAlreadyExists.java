package com.system.online_exam_system.exam.exceptions;

import com.system.online_exam_system.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class QuestionAlreadyExists extends ApiException {
    public QuestionAlreadyExists() {
        super("Question already exists", HttpStatus.BAD_REQUEST);
    }
}
