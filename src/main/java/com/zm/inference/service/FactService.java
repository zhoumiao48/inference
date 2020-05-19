package com.zm.inference.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.zm.inference.mapper.FactMapper;
import com.zm.inference.domain.Fact;

import java.util.List;

@Service
public class FactService {

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

    /**
     * 检查传入的factList中的每个fact是否存在数据库中，不存在则插入，最终给原有的每个fact加上id
     */
    public List<Fact> checkFactList(List<Fact> origFacts) {

        for (Fact tmpFact : origFacts) {
            List<Integer> factIds = factMapper.selectIdByFAttributeAndFValue(
                    tmpFact.getFAttribute(), tmpFact.getFValue());
            if (factIds.size() == 1) {
                // 如果原系统中存在该条事实知识 -> 直接修改fact的值
                tmpFact.setId(factIds.get(0));
            }else{
                // 原系统中不存在该条事实知识 -> 需要进行事实知识的插入，插入之后同样需要把原有的fact对象加上id值
                // 这里的insertSelective默认就会把tmpFact加上id
                insertSelective(tmpFact);
            }
        }
        // 返回添加了id之后的事实列表
        return origFacts;
    }
}
