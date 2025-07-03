package com.system.online_exam_system.user.mappers;

import com.system.online_exam_system.user.dtos.CreateUserRequest;
import com.system.online_exam_system.user.dtos.StudentResponse;
import com.system.online_exam_system.user.entites.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toStudent(CreateUserRequest request);
    StudentResponse toStudentResponse(Student student);
}
