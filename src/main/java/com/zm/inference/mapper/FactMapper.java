package com.zm.inference.mapper;

import com.zm.inference.domain.Fact;

public interface FactMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Fact record);

    int insertSelective(Fact record);

    Fact selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Fact record);

    int updateByPrimaryKey(Fact record);
}