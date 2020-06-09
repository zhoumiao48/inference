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
    List<Integer> selectDistinctPIdByFIdIn(@Param("fIdCollection") Collection<Integer> fIdCollection);

    /**
     * 根据pid的集合来查找对应的fact_id
     */
    List<Integer> selectFIdByPId(@Param("pId") Integer pId);

    /**
     * 批量插入（已测试）
     */
    int insertList(@Param("list") List<MapPatternFact> list);
}