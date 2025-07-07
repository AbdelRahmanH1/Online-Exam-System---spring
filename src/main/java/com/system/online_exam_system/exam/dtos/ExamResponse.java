package com.system.online_exam_system.exam.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamResponse {
    private Long id;
    private String examName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;
    private int gradeLevel;
    private String instructorName;

}
