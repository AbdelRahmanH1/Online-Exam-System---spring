package com.system.online_exam_system.user.controllers;

import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.user.services.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructors")
@AllArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<?> getAllInstructors(@RequestParam(name = "page",required = false,defaultValue = "0") int page){
        var response = instructorService.getAllInstructors(page);
        return ResponseUtil.success("get all instructors", response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstructorById(@PathVariable(name = "id") long id){
        var response = instructorService.getInstructorById(id);
        return ResponseUtil.success("get instructor", response);
    }
}
