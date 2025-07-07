package com.system.online_exam_system.exam.mappers;

import com.system.online_exam_system.exam.dtos.AddQuestionRequest;
import com.system.online_exam_system.exam.dtos.QuestionResponse;
import com.system.online_exam_system.exam.entites.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(source = "question",target = "questionText")
    @Mapping(source = "answer",target = "correctAnswer")
    Question toEntity(AddQuestionRequest request);

    @Mapping(source = "exam.id",target = "exam_Id")
    QuestionResponse toResponse(Question question);
}
