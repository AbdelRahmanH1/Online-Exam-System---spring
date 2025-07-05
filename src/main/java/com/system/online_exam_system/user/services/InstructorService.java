package com.system.online_exam_system.user.services;

import com.system.online_exam_system.common.exceptions.InstructorNotFound;
import com.system.online_exam_system.common.utils.BuildPageable;
import com.system.online_exam_system.user.dtos.InstructorResponse;
import com.system.online_exam_system.user.mappers.InstructorMapper;
import com.system.online_exam_system.user.repositories.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstructorService {
    private final int PAGE_SIZE = 10;
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    public Page<InstructorResponse>getAllInstructors(int pageNumber) {
        var pageable = BuildPageable.of(pageNumber,PAGE_SIZE);
        var instructors = instructorRepository.findAll(pageable);
        return instructors.map(instructorMapper::toInstructorResponse);
    }

    public InstructorResponse getInstructorById(Long id) {
        var  instructor = instructorRepository.findById(id).orElseThrow(InstructorNotFound::new);
        return instructorMapper.toInstructorResponse(instructor);
    }
}
