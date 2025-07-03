package com.system.online_exam_system.user.services;

import com.system.online_exam_system.user.dtos.StudentResponse;
import com.system.online_exam_system.user.mappers.StudentMapper;
import com.system.online_exam_system.user.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    public Page<StudentResponse> getStudents(Integer pageNumber) {
        int page = (pageNumber == null || pageNumber<0) ? 0 : pageNumber;
        Pageable pageable = PageRequest.of(page, 10);
        var students = studentRepository.findAll(pageable);
        return students.map(studentMapper::toStudentResponse);
    }
}
