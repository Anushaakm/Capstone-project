package com.anu.capstone.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DynamicMapper {

    public <T, U> U convertor(T entity, U dto) {
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
