package com.system.online_exam_system.exam.controllers;

import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.exam.dtos.SubmitExamAttemptRequest;
import com.system.online_exam_system.exam.services.ExamAttemptService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams/attempts")
@AllArgsConstructor
public class ExamAttemptController {

    private final ExamAttemptService examAttemptService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/{examId}/start")
    public ResponseEntity<?> startExam(@PathVariable Long examId) {
        var response = examAttemptService.startExam(examId);
        return ResponseUtil.success("Exam has been started", response);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/{attemptId}/submit")
    public ResponseEntity<?> submitExam(@PathVariable Long attemptId,
                                        @Valid @RequestBody SubmitExamAttemptRequest request) {

        var response = examAttemptService.submitExamAttempt(attemptId,request);
        return ResponseUtil.success("Exam has been submitted", response);
    }
}
