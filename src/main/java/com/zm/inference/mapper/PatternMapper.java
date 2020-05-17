package com.zm.inference.mapper;

import com.zm.inference.domain.Pattern;

public interface PatternMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pattern record);

    int insertSelective(Pattern record);

    Pattern selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pattern record);

    int updateByPrimaryKey(Pattern record);
}