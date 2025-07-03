package com.system.online_exam_system.user.dtos;

import lombok.Data;

@Data
public class StudentResponse {
    private Long id;
    private String name;
    private String username;
    private int grade;
}
