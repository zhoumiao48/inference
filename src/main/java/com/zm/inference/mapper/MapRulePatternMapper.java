package com.zm.inference.mapper;

import com.zm.inference.domain.mapClass.MapRulePattern;

public interface MapRulePatternMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapRulePattern record);

    int insertSelective(MapRulePattern record);

    MapRulePattern selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MapRulePattern record);

    int updateByPrimaryKey(MapRulePattern record);
}