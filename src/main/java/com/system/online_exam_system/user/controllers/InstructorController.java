package com.system.online_exam_system.user.controllers;

import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.user.services.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructors")
@PreAuthorize("hasRole('ADMIN')")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstructorById(@PathVariable(name = "id") long id){
        instructorService.deleteInstructorById(id);
        return ResponseUtil.success("delete instructor", id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchInstructor(
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "page",required = false,defaultValue = "0")  int page,
            @RequestParam(name = "department",required = false) String department
    ){
        var response = instructorService.searchInstructors(name,department,page);
        return ResponseUtil.success("search instructor", response);
    }
}
