package com.system.online_exam_system.user.services;

import com.system.online_exam_system.common.exceptions.InstructorNotFound;
import com.system.online_exam_system.common.utils.BuildPageable;
import com.system.online_exam_system.user.dtos.InstructorResponse;
import com.system.online_exam_system.user.entites.Instructor;
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

    public void deleteInstructorById(Long id) {
        if(!instructorRepository.existsById(id)) {
            throw new InstructorNotFound();
        }
        instructorRepository.deleteById(id);
    }

    private Page<Instructor> getInstructorsByName(String name, int pageNumber) {
        var pageable = BuildPageable.of(pageNumber,PAGE_SIZE);
        return instructorRepository.findByNameContainingIgnoreCase(name,pageable);
    }

    private Page<Instructor> getInstructorsByDepartment(String department, int pageNumber) {
        var pageable = BuildPageable.of(pageNumber,PAGE_SIZE);
        return instructorRepository.findByDepartmentContainingIgnoreCase(department,pageable);
    }

    private Page<Instructor> getInstructorsByDepartmentAndName(String department, String name, int pageNumber) {
        var pageable = BuildPageable.of(pageNumber,PAGE_SIZE);
        return instructorRepository.findByNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(name,department,pageable);
    }

    public Page<InstructorResponse> searchInstructors(String name, String department,int pageNumber) {
        Page<Instructor> result;
        boolean hasName = name != null && !name.isBlank();
        boolean hasDepartment = department != null && !department.isBlank();

        if(hasName && hasDepartment) {
            result = getInstructorsByDepartmentAndName(name,department,pageNumber);
        } else if (hasName) {
            result = getInstructorsByName(name,pageNumber);
        } else if (hasDepartment) {
            result = getInstructorsByDepartment(department,pageNumber);
        }else {
            result = instructorRepository.findAll(BuildPageable.of(pageNumber,PAGE_SIZE));
        }
        return  result.map(instructorMapper::toInstructorResponse);
    }

}
