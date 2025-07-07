package com.system.online_exam_system.exam.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddQuestionRequest {
    @NotBlank(message = "Question text must not be blank")
    private String question;

    @NotBlank(message = "Correct answer must not be blank")
    private String answer;

    @Positive(message = "Points must be greater than 0")
    private double points;
}
