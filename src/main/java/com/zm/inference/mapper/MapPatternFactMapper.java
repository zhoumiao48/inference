package com.zm.inference.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

import com.zm.inference.domain.mapClass.MapPatternFact;

import java.util.List;

public interface MapPatternFactMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapPatternFact record);

    int insertSelective(MapPatternFact record);

    MapPatternFact selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MapPatternFact record);

    int updateByPrimaryKey(MapPatternFact record);

    /**
     * 根据fid的集合来查找对应的pattern_id
     */
    List<Integer> selectPIdByFIdIn(@Param("fIdCollection") Collection<Integer> fIdCollection);

    /**
     * 批量插入（已测试）
     */
    int insertList(@Param("list") List<MapPatternFact> list);
}