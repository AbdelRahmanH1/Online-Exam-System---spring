package com.system.online_exam_system.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class BuildPageable {

    private BuildPageable(){}

    public static Pageable of(Integer pageNumber,Integer size) {
        int page = (pageNumber == null || pageNumber < 0) ? 0 : pageNumber;
        int pageSize = (size == null || size < 0) ? 10 : size;
        return PageRequest.of(page, pageSize);
    }
    public static Pageable of(Integer pageNumber, Integer size , Sort sort){
        int page = (pageNumber == null || pageNumber < 0) ? 0 : pageNumber;
        int pageSize = (size == null || size < 0) ? 10 : size;
        return PageRequest.of(page, pageSize,sort);
    }
}
