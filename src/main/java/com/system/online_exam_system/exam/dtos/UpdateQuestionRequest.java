package com.system.online_exam_system.exam.dtos;


import lombok.Data;

@Data
public class UpdateQuestionRequest {
    private String question;

    private String answer;

    private Double points;
}
