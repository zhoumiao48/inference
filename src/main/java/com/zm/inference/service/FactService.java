package com.zm.inference.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zm.inference.mapper.FactMapper;
import com.zm.inference.domain.Fact;
@Service
public class FactService{

    @Resource
    private FactMapper factMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return factMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Fact record) {
        return factMapper.insert(record);
    }

    
    public int insertSelective(Fact record) {
        return factMapper.insertSelective(record);
    }

    
    public Fact selectByPrimaryKey(Integer id) {
        return factMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Fact record) {
        return factMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Fact record) {
        return factMapper.updateByPrimaryKey(record);
    }

}
