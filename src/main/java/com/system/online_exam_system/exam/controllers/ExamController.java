package com.system.online_exam_system.exam.controllers;


import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.exam.dtos.CreateExamRequest;
import com.system.online_exam_system.exam.dtos.UpdateExamRequest;
import com.system.online_exam_system.exam.services.ExamService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
@AllArgsConstructor
public class ExamController {

    private ExamService examService;

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping
    public ResponseEntity<?> createExam(@Valid @RequestBody CreateExamRequest request) {
       var response =  examService.createExam(request);
       return ResponseUtil.success("Create exam successfully", response,HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateExam(@PathVariable Long id, @Valid @RequestBody UpdateExamRequest request) {
        var response =  examService.updateExam(id, request);
        return ResponseUtil.success("Update exam successfully", response);
    }
}
