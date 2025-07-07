package com.system.online_exam_system.exam.mappers;

import com.system.online_exam_system.exam.dtos.CreateExamRequest;
import com.system.online_exam_system.exam.dtos.ExamResponse;
import com.system.online_exam_system.exam.dtos.UpdateExamRequest;
import com.system.online_exam_system.exam.entites.Exam;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    @Mapping(source = "grade_level",target = "gradeLevel")
    Exam toExam(CreateExamRequest request);

    @Mapping(source = "instructor.name",target = "instructorName")
    @Mapping(source = "title",target = "examName")
    ExamResponse toExamResponse(Exam exam);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExamFromDto(UpdateExamRequest request, @MappingTarget Exam exam);
}
