package com.zm.inference.mapper;

import com.zm.inference.domain.mapClass.MapPatternFact;

public interface MapPatternFactMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapPatternFact record);

    int insertSelective(MapPatternFact record);

    MapPatternFact selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MapPatternFact record);

    int updateByPrimaryKey(MapPatternFact record);
}