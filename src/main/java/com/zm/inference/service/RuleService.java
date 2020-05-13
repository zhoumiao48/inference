package com.zm.inference.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zm.inference.mapper.RuleMapper;
import com.zm.inference.domain.Rule;
@Service
public class RuleService{

    @Resource
    private RuleMapper ruleMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return ruleMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Rule record) {
        return ruleMapper.insert(record);
    }

    
    public int insertSelective(Rule record) {
        return ruleMapper.insertSelective(record);
    }

    
    public Rule selectByPrimaryKey(Integer id) {
        return ruleMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Rule record) {
        return ruleMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Rule record) {
        return ruleMapper.updateByPrimaryKey(record);
    }

}
