package com.system.online_exam_system.user.services;

import com.system.online_exam_system.common.exceptions.StudentNotFound;
import com.system.online_exam_system.user.dtos.StudentResponse;
import com.system.online_exam_system.user.entites.Student;
import com.system.online_exam_system.user.mappers.StudentMapper;
import com.system.online_exam_system.user.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    private Pageable buildPageable(Integer pageNumber) {
        int page = (pageNumber == null || pageNumber < 0) ? 0 : pageNumber;
        return PageRequest.of(page, 10);
    }

    public Page<StudentResponse> getStudents(Integer pageNumber) {
        var pageable = buildPageable(pageNumber);
        var students = studentRepository.findAll(pageable);
        return students.map(studentMapper::toStudentResponse);
    }

    public StudentResponse getStudentById(Long id) {
        var student = studentRepository.findById(id).orElseThrow(StudentNotFound::new);
        return studentMapper.toStudentResponse(student);
    }

    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFound();
        }
        studentRepository.deleteById(id);
    }

    private Page<Student> getStudentsByGrade(int grade, Integer pageNumber) {
        var pageable = buildPageable(pageNumber);
        return studentRepository.findByGrade(grade,pageable);
    }

    private Page<Student> getStudentsByName(String name, Integer pageNumber) {
        var pageable = buildPageable(pageNumber);
        return studentRepository.findByNameContainingIgnoreCase(name,pageable);

    }
    private Page<Student> getStudentsByNameAndGrade(String name,int grade, Integer pageNumber) {
        var pageable = buildPageable(pageNumber);
        return   studentRepository.findByNameContainingIgnoreCaseAndGrade(name,grade,pageable);

    }
    public Page<StudentResponse> searchStudents(String name, Integer grade,Integer pageNumber) {

        Page<Student> result;
        boolean hasName = name != null && !name.isBlank();
        boolean hasGrade =  grade != null;

        if(hasName && hasGrade) {
            result = getStudentsByNameAndGrade(name,grade,pageNumber);
        }else if(hasName) {
            result = getStudentsByName(name,pageNumber);
        }else if(hasGrade) {
            result = getStudentsByGrade(grade,pageNumber);
        }else{
            result = studentRepository.findAll(buildPageable(pageNumber));
        }
        return result.map(studentMapper::toStudentResponse);
    }
}
