package com.zm.inference.service;

import com.zm.inference.domain.Fact;
import com.zm.inference.domain.mapClass.MapPatternFact;
import com.zm.inference.mapper.MapPatternFactMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.zm.inference.mapper.PatternMapper;
import com.zm.inference.domain.Pattern;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatternService {

    @Resource
    private PatternMapper patternMapper;

    @Resource
    private MapPatternFactMapper mapPatternFactMapper;

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


    /**
     * 检查当前模式是否记录在数据库中，如果模式存在则返回pattern_id，不存在则插入之后返回pattern_id
     *
     * @param factList        该模式对应的fact列表
     * @param patternPriority 该模式的权重
     * @return 返回查找后的Pattern的Id或者是插入Pattern后的id
     * @author zm
     */
    public Integer checkPattern(List<Fact> factList, Float patternPriority) {
        int size = factList.size();
        List<Integer> factIdList = new ArrayList<>(size);

        for (Fact fact : factList) {
            factIdList.add(fact.getId());
        }

        List<Integer> patternIds = mapPatternFactMapper.selectDistinctPIdByFIdIn(factIdList);
        if (patternIds.size() == 0) {
            // 数据库中不存在该模式
            // 插入新模式
            Pattern newPattern = new Pattern();
            newPattern.setIsMulti((byte) (size > 1 ? 1 : 0));
            newPattern.setPriority(patternPriority);
            Integer newPatternId = patternMapper.insertSelective(newPattern);
            // 插入map_pattern_fact
            List<MapPatternFact> patternFactList = new ArrayList<>();
            for (Integer factId : factIdList) {
                MapPatternFact patternFact = new MapPatternFact();
                patternFact.setPId(newPatternId);
                patternFact.setFId(factId);
                patternFactList.add(patternFact);
            }
            mapPatternFactMapper.insertList(patternFactList);

            return newPatternId;
        } else {
            // 数据库中存在该模式，直接返回该pattern的id
            return patternIds.get(0);
        }
    }
}
