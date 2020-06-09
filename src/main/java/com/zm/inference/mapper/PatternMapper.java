package com.zm.inference.mapper;

import com.zm.inference.domain.subClass.SubPattern;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Date;

import com.zm.inference.domain.Pattern;

public interface PatternMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pattern record);

    int insertSelective(Pattern record);

    Pattern selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pattern record);

    int updateByPrimaryKey(Pattern record);

    List<Pattern> selectByAll(Pattern pattern);

    List<SubPattern> selectAll();

    /**
     * 根据ruleId查找返回相关的模式集合
     */
    List<SubPattern> selectByRuleId(Integer ruleId);
}