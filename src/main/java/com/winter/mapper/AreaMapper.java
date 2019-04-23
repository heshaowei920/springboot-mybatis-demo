package com.winter.mapper;

import com.winter.model.Area;

public interface AreaMapper {
    int insert(Area record);

    int insertSelective(Area record);
}