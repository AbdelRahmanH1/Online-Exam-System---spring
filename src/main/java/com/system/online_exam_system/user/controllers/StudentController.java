package com.system.online_exam_system.user.controllers;

import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.user.dtos.UpdateGradeRequest;
import com.system.online_exam_system.user.services.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAnyRole('INSTRUCTOR','ADMIN')")
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAllStudents(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        var response = studentService.getStudents(page);
        return ResponseUtil.success("get all students", response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable(name = "id") Long id) {
        var response = studentService.getStudentById(id);
        return ResponseUtil.success("get student by id", response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable(name = "id") Long id) {
        studentService.deleteStudentById(id);
        return ResponseUtil.success("delete student by id", null);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchStudents(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "grade", required = false) Integer grade,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page
            ) {
                var response =  studentService.searchStudents(name,grade,page);
                return  ResponseUtil.success("search student by name and grade", response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody UpdateGradeRequest request , @PathVariable(name = "id") long id) {
        studentService.updateStudent(id,request);
        return  ResponseUtil.success("update student by id", null);
    }
}
