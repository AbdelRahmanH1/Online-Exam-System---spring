package com.system.online_exam_system.exam.controllers;


import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.exam.dtos.CreateExamRequest;
import com.system.online_exam_system.exam.dtos.UpdateExamRequest;
import com.system.online_exam_system.exam.services.ExamService;
import com.system.online_exam_system.user.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
@PreAuthorize("hasRole('INSTRUCTOR')")
@AllArgsConstructor
@Tag(name = "Exams", description = "Endpoints for instructors to manage exams, and for students to view them")
@SecurityRequirement(name = "bearerAuth")
public class ExamController {

    private final StudentService studentService;
    private ExamService examService;


    @PostMapping
    @Operation(summary = "Create a new exam")
    public ResponseEntity<?> createExam(@Valid @RequestBody CreateExamRequest request) {
       var response =  examService.createExam(request);
       return ResponseUtil.success("Create exam successfully", response,HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    @Operation(summary = "Update an existing exam by ID")
    public ResponseEntity<?> updateExam(@PathVariable Long id, @Valid @RequestBody UpdateExamRequest request) {
        var response =  examService.updateExam(id, request);
        return ResponseUtil.success("Update exam successfully", response);
    }

    @GetMapping
    @Operation(summary = "Get all exams (paginated)")
    public ResponseEntity<?> getAllExams(@RequestParam(name = "page",required = false,defaultValue = "0") int page){
        var response =  examService.getAllExams(page);
        return ResponseUtil.success("Get exams successfully", response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get exam details by ID")
    public ResponseEntity<?> getExamById(@PathVariable Long id){
        var response =  examService.getExamById(id);
        return ResponseUtil.success("Get exam successfully", response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete exam by ID")
    public ResponseEntity<?> deleteExamById(@PathVariable Long id){
        examService.deleteExamById(id);
        return ResponseUtil.success("Delete exam successfully",null);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/available-exams")
    @Operation(summary = "Get available exams for student (not yet attempted)")
    public ResponseEntity<?> getAllAvailableExams(@RequestParam(name = "page",defaultValue = "0",required = false) int page){
        var response = studentService.getAvailableExams(page);
        return ResponseUtil.success("Get available exams successfully", response);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/past-exams")
    @Operation(summary = "Get past exam attempts for the student")
    public ResponseEntity<?> getAllPastExams(@RequestParam(name = "page",defaultValue = "0") int page){
        var response = studentService.getPastExamAttempts(page);
        return  ResponseUtil.success("Get past exams successfully", response);
    }

}
