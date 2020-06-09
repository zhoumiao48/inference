package com.zm.inference.mapper;

import com.zm.inference.domain.Rule;
import com.zm.inference.domain.subClass.SubRule;

import java.util.List;

public interface RuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rule record);

    int insertSelective(Rule record);

    Rule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rule record);

    int updateByPrimaryKey(Rule record);


    /**
     * 查询所有的规则知识（封装相应的模式）
     */
    List<SubRule> selectAllRule();
}