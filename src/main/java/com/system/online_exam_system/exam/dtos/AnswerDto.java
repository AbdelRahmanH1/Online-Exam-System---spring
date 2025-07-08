package com.system.online_exam_system.exam.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnswerDto {
    @NotNull(message = "Question ID is required")
    private Long questionId;

    @NotNull(message = "Answer cannot be null")
    private String answer;
}
