package com.system.online_exam_system.exam.controllers;

import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.exam.services.ExamAttemptService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exams/attempts")
@AllArgsConstructor
public class ExamAttemptController {

    private final ExamAttemptService examAttemptService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("{examId}/start")
    public ResponseEntity<?> startExam(@PathVariable Long examId) {
        var response = examAttemptService.startExam(examId);
        return ResponseUtil.success("Exam has been started", response);
    }
}
