package com.zm.inference.service;

import com.zm.inference.domain.Fact;
import com.zm.inference.domain.mapClass.MapPatternFact;
import com.zm.inference.domain.subClass.SubPattern;
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
    private MapPatternFactMapper mpfMapper;

    @Resource
    private FactService factService;

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
     * @param factList 该模式对应的fact列表
     * @param isMulti  是否是需要同时满足1：全部都要满足
     * @return 返回查找后的Pattern的Id或者是插入Pattern后的id
     * @author zm
     */
    public Integer checkPattern(List<Fact> factList, Byte isMulti) {
        int size = factList.size();
        List<Integer> factIdList = new ArrayList<>(size);

        for (Fact fact : factList) {
            factIdList.add(fact.getId());
        }

        List<Integer> patternIds = mpfMapper.selectDistinctPIdByFIdIn(factIdList);
        if (patternIds.size() == 0) {
            // 数据库中不存在该模式
            // 插入该新模式
            Pattern newPattern = new Pattern();
            // 修改：模式权值放在map_rule_pattern中
            // newPattern.setWeight(patternPriority);
            newPattern.setIsMulti(isMulti);
            patternMapper.insertSelective(newPattern);
            Integer newPatternId = newPattern.getId();

            // 插入map_pattern_fact
            List<MapPatternFact> patternFactList = new ArrayList<>();
            for (Integer factId : factIdList) {
                MapPatternFact patternFact = new MapPatternFact();
                patternFact.setPId(newPatternId);
                patternFact.setFId(factId);
                patternFactList.add(patternFact);
            }
            mpfMapper.insertList(patternFactList);

            return newPatternId;
        } else {
            return patternIds.get(0);
        }
    }

    /**
     * 获取系统中的模式集合（内部封装了相应的事实知识）
     *
     * @author zm
     * @param []
     * @return java.util.List<com.zm.inference.domain.subClass.SubPattern>
     * @date 2020/6/9 8:40
     **/
    public List<SubPattern> getPatternList() {
        // 首先去查找us噢哟
        List<SubPattern> subPatternList = patternMapper.selectAll();

        for (int i = 0; i < subPatternList.size(); i++) {
            SubPattern sp = subPatternList.get(i);
            // 设置对应的事实
            sp.setFactList(factService.getFactsByPid(sp.getId()));
        }
        return subPatternList;
    }
}
