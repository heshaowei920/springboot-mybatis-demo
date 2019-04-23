package com.winter.mapper;

import com.winter.model.Kk;

public interface KkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Kk record);

    int insertSelective(Kk record);

    Kk selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Kk record);

    int updateByPrimaryKey(Kk record);
}