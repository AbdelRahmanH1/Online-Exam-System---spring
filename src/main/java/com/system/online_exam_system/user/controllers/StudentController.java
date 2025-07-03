package com.system.online_exam_system.user.controllers;

import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.user.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAllStudents(@RequestParam(name = "page",required = false,defaultValue = "0") Integer page) {
        var response = studentService.getStudents(page);
        return ResponseUtil.success("get all students",response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable(name = "id") Long id) {
        var response = studentService.getStudentById(id);
        return ResponseUtil.success("get student by id",response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable(name = "id") Long id) {
        studentService.deleteStudentById(id);
        return ResponseUtil.success("delete student by id",null);
    }
}
