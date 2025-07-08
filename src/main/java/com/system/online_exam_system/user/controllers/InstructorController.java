package com.system.online_exam_system.user.controllers;

import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.user.services.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructors")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
@Tag(name = "Instructors", description = "Admin-only endpoints to manage instructors")
@SecurityRequirement(name = "bearerAuth")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping
    @Operation(summary = "Get all instructors", description = "Returns paginated list of instructors")
    public ResponseEntity<?> getAllInstructors(@RequestParam(name = "page",required = false,defaultValue = "0") int page){
        var response = instructorService.getAllInstructors(page);
        return ResponseUtil.success("get all instructors", response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get instructor by ID", description = "Returns a specific instructor's data")
    public ResponseEntity<?> getInstructorById(@PathVariable(name = "id") long id){
        var response = instructorService.getInstructorById(id);
        return ResponseUtil.success("get instructor", response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete instructor by ID", description = "Deletes an instructor")
    public ResponseEntity<?> deleteInstructorById(@PathVariable(name = "id") long id){
        instructorService.deleteInstructorById(id);
        return ResponseUtil.success("delete instructor", id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search instructors", description = "Search by name or department")
    public ResponseEntity<?> searchInstructor(
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "page",required = false,defaultValue = "0")  int page,
            @RequestParam(name = "department",required = false) String department
    ){
        var response = instructorService.searchInstructors(name,department,page);
        return ResponseUtil.success("search instructor", response);
    }
}
