package com.system.online_exam_system.exam.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class SubmitExamAttemptRequest {
    @NotEmpty(message = "Answers list cannot be empty")
    private List<AnswerDto> answers;
}
