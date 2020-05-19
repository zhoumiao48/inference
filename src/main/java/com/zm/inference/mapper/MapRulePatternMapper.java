package com.zm.inference.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Collection;

import com.zm.inference.domain.mapClass.MapRulePattern;

public interface MapRulePatternMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapRulePattern record);

    int insertSelective(MapRulePattern record);

    MapRulePattern selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MapRulePattern record);

    int updateByPrimaryKey(MapRulePattern record);

    int insertList(@Param("list")List<MapRulePattern> list);

    List<Integer> selectDistinctRIdByPIdIn(@Param("pIdCollection")Collection<Integer> pIdCollection);
}