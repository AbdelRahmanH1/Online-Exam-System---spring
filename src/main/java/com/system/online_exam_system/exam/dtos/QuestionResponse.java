package com.system.online_exam_system.exam.dtos;

import lombok.Data;

@Data
public class QuestionResponse {
    private Long id;
    private String questionText;
    private String correctAnswer;
    private double points;
    private Long exam_Id;

}
