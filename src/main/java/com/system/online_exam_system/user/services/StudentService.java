package com.system.online_exam_system.user.services;

import com.system.online_exam_system.common.exceptions.ApiException;
import com.system.online_exam_system.common.exceptions.StudentNotFound;
import com.system.online_exam_system.common.utils.BuildPageable;
import com.system.online_exam_system.user.dtos.StudentResponse;
import com.system.online_exam_system.user.dtos.UpdateGradeRequest;
import com.system.online_exam_system.user.entites.Student;
import com.system.online_exam_system.user.mappers.StudentMapper;
import com.system.online_exam_system.user.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final int PAGE_SIZE = 10;


    public Page<StudentResponse> getStudents(Integer pageNumber) {
        var pageable = BuildPageable.of(pageNumber, PAGE_SIZE);
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
        var pageable = BuildPageable.of(pageNumber, PAGE_SIZE);
        return studentRepository.findByGrade(grade,pageable);
    }

    private Page<Student> getStudentsByName(String name, Integer pageNumber) {
        var pageable = BuildPageable.of(pageNumber, PAGE_SIZE);
        return studentRepository.findByNameContainingIgnoreCase(name,pageable);

    }
    private Page<Student> getStudentsByNameAndGrade(String name,int grade, Integer pageNumber) {
        var pageable = BuildPageable.of(pageNumber, PAGE_SIZE);
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
            result = studentRepository.findAll(BuildPageable.of(pageNumber,PAGE_SIZE));
        }
        return result.map(studentMapper::toStudentResponse);
    }
    public void updateStudent(long id, UpdateGradeRequest request) {
        var student = studentRepository.findById(id).orElseThrow(StudentNotFound::new);
        if(!student.canPromoteTo(request.getGrade())) {
            throw new ApiException("Can't descend from grade", HttpStatus.BAD_REQUEST);
        }
        student.promoteTo(request.getGrade());
        studentRepository.save(student);
    }
}
