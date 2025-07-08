package com.system.online_exam_system.exam.exceptions;

import com.system.online_exam_system.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class ExamTimeFinishedException extends ApiException {
    public ExamTimeFinishedException() {
        super("The exam has already ended. You can't start it anymore.", HttpStatus.FORBIDDEN);
    }
}
