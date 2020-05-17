package com.zm.inference.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zm.inference.mapper.PatternMapper;
import com.zm.inference.domain.Pattern;
@Service
public class PatternService{

    @Resource
    private PatternMapper patternMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return patternMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Pattern record) {
        return patternMapper.insert(record);
    }

    
    public int insertSelective(Pattern record) {
        return patternMapper.insertSelective(record);
    }

    
    public Pattern selectByPrimaryKey(Integer id) {
        return patternMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Pattern record) {
        return patternMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Pattern record) {
        return patternMapper.updateByPrimaryKey(record);
    }

}
