package com.system.online_exam_system.exam.exceptions;

import com.system.online_exam_system.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class ExamNotStartedYetException extends ApiException {
    public ExamNotStartedYetException() {
        super("The exam hasn't started yet. Please try again later.", HttpStatus.FORBIDDEN);
    }
}
