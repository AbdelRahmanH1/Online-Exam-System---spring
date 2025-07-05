package com.system.online_exam_system.user.mappers;

import com.system.online_exam_system.user.dtos.CreateUserRequest;
import com.system.online_exam_system.user.dtos.InstructorResponse;
import com.system.online_exam_system.user.entites.Instructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    Instructor toInstructor(CreateUserRequest request);
    InstructorResponse toInstructorResponse(Instructor instructor);
}
