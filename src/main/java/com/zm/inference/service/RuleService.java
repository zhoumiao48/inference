package com.zm.inference.service;

import com.zm.inference.common.util.StringTool;
import com.zm.inference.domain.Fact;
import com.zm.inference.domain.mapClass.MapRulePattern;
import com.zm.inference.domain.plusClass.PlusUser;
import com.zm.inference.domain.subClass.SubPattern;
import com.zm.inference.domain.subClass.SubRule;
import com.zm.inference.mapper.MapRulePatternMapper;
import com.zm.inference.service.Interface.RuleServiceInterface;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.zm.inference.mapper.RuleMapper;
import com.zm.inference.domain.Rule;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RuleService implements RuleServiceInterface {

    @Resource
    private RuleMapper ruleMapper;

    @Resource
    private FactService factService;

    @Resource
    private PatternService patternService;

    @Resource
    private MapRulePatternMapper mapRulePatternMapper;

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

    @Override
    @Transient
    public Boolean addNewRule(SubRule subRule) {
        // step1：首先看规则中所含的事实fact是否存在于系统中，如果不存在就需要进行插入，然后都需要给fact加上id值
        List<SubPattern> frontPatterns = subRule.getFrontPatternList();
        List<SubPattern> backPatterns = subRule.getBackPatternList();

        // 处理前件
        for (SubPattern tmpPattern :
                frontPatterns) {
            // 获取当前SubPattern中的factList
            List<Fact> factList = tmpPattern.getFactList();
            // 给factList中的fact添加上id（后一步需要根据fact_id来判断数据库中是否存在pattern）
            tmpPattern.setFactList(factService.checkFactList(factList));
            // 给frontPatterns中的每一个SubPattern添加上id
            tmpPattern.setId(patternService.checkPattern(factList, tmpPattern.getPriority()));
        }

        // 检查数据库中是否已经存在此规则，如果存在直接返回false
        if(checkRule(frontPatterns) == -1){
            return false;
        }

        // 处理后件
        for (SubPattern tmpPattern :
                backPatterns) {
            List<Fact> factList = tmpPattern.getFactList();
            tmpPattern.setFactList(factService.checkFactList(factList));
            tmpPattern.setId(patternService.checkPattern(factList, tmpPattern.getPriority()));
        }

        // 把原有iText中的占位数字字符替换成 pattern_id
        String iText = subRule.getiText();
        String[] symbolArr = iText.split(" ");
        for (int i = 0; i < symbolArr.length; i++) {
            if ("^".equals(symbolArr[i]) || "v".equals(symbolArr[i])) {
                continue;
            }
            symbolArr[i] = String.valueOf(frontPatterns.get(i).getId());
        }
        String modifiedIText = "";
        for (String s : symbolArr) {
            modifiedIText = s + " ";
        }
        subRule.setiText(modifiedIText);

        // 中缀表达式转后缀表达式
        String rText = StringTool.transRTextWithIText(modifiedIText);
        subRule.setrText(rText);

        Date dateNow = new Date();
        subRule.setCreatedTime(dateNow);
        subRule.setModifiedTime(dateNow);
        // 插入新规则
        Integer newRuleId = ruleMapper.insertSelective(subRule);
        // 插入rule_pattern关联——前件
        List<MapRulePattern> mrpList = new ArrayList<>(frontPatterns.size() + backPatterns.size());
        for(SubPattern sp:frontPatterns){
            MapRulePattern mrp1 = new MapRulePattern();
            mrp1.setRId(newRuleId);
            mrp1.setPId(sp.getId());
            // 0代表是前件
            mrp1.setIsFront((byte) 0);
            mrpList.add(mrp1);
        }
        // 插入rule_pattern关联——后件
        for (SubPattern sp:backPatterns){
            MapRulePattern mrp2 = new MapRulePattern();
            mrp2.setRId(newRuleId);
            mrp2.setPId(sp.getId());
            // 其代表是后件
            mrp2.setIsFront((byte) 1);
            mrpList.add(mrp2);
        }
        mapRulePatternMapper.insertList(mrpList);
        return true;
    }

    @Override
    public Integer checkRule(List<SubPattern> frontPatterns) {
        int size = frontPatterns.size();
        List<Integer> patternIds = new ArrayList<>(size);
        for (SubPattern frontPattern : frontPatterns) {
            patternIds.add(frontPattern.getId());
        }
        List<Integer> ruleIdList = mapRulePatternMapper.selectRIdByPIdIn(patternIds);
        if (ruleIdList.size() == 1) {
            return ruleIdList.get(0);
        }
        return -1;
    }
}
