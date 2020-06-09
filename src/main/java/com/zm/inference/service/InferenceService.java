package com.zm.inference.service;

import com.zm.inference.domain.subClass.SubPattern;
import com.zm.inference.domain.subClass.SubRule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/5/20 9:57
 **/
@Service
public class InferenceService {

    @Resource
    private RuleService ruleService;

    /**
     * 推理的核心部分
     *
     * @param subPatterns 初始已知的模式（按照patternId有序）
     * @return java.util.List<com.zm.inference.domain.subClass.SubPattern>
     * @author zm
     * @date 2020/6/9 20:37
     **/
    public List<SubPattern> doInference(List<SubPattern> subPatterns) {
        // 推理结论
        List<SubPattern> ansPatterns = new ArrayList<>();

        // step1:首先获取所有封装了模式的规则
        List<SubRule> allRules = ruleService.getAllRule();

        // step2：获取规则模式对应的map，便于后续判断模式是否在规则前件中
        Map<Integer, ArrayList<Integer>> mapRulePattern = ruleService.getRulePatternMap();

        // step3：遍历每一条规则
        for (SubRule sr : allRules) {
            // 当前规则的前件模式
            List<SubPattern> frontPatterns = sr.getFrontPatternList();
            // 当前规则前件模式的id集合
            ArrayList<Integer> patternIds = mapRulePattern.get(sr.getId());

            // 构建一个临时的前件集合用于后续的可信度计算
            List<SubPattern> calFrontPatterns = new ArrayList<>();

            int tmpCount = patternIds.size();

            // 遍历动态库中的已有模式
            for (SubPattern sp : subPatterns) {
                Integer spId = sp.getId();
                if (patternIds.indexOf(spId) != -1) {
                    // 当前模式存在于当前规则的前件中
                    SubPattern origPattern = null;
                    for (SubPattern fsp : frontPatterns) {
                        if (spId.equals(fsp.getId())) {
                            origPattern = fsp;
                            break;
                        }
                    }
                    // 需要检查fact个数是否满足要求（0表示的是只要满足其中一个即可，1要求全部满足）
                    if(origPattern.getIsMulti() == 1){
                        if(origPattern.getFactList().size() != sp.getFactList().size()){
                            // 如果模式数量不满足条件
                            continue;
                        }
                    }

                    // 需要检查weight值是否达标
                    if (sp.getWeight() >= origPattern.getWeight()) {
                        // 把已有模式加入calFrontPatterns中
                        calFrontPatterns.add(sp);
                        // 计数值减一
                        tmpCount--;
                    }
                }
            }

            if (tmpCount == 0) {
                // 证明当前规则的前件模式都满足要求，则该条规则被激活
                // 计算结论的可信度，
                SubPattern backPattern = sr.getBackPatternList().get(0);
                String[] formula = sr.getRText().split(" ");

                Stack<Double> ansStack = new Stack<>();

                for (String s : formula) {
                    if (Objects.equals(s, "^")) {
                        Double d1 = ansStack.pop();
                        Double d2 = ansStack.pop();
                        ansStack.push(d1 > d2 ? d2 : d1);
                    } else if (Objects.equals(s, "v")) {
                        Double d1 = ansStack.pop();
                        Double d2 = ansStack.pop();
                        ansStack.push(d1 > d2 ? d1 : d2);
                    } else {
                        // 如果是模式就入栈（用到calFrontPatterns，即weight值是实际值的前件模式集合)
                        ansStack.push(calFrontPatterns.get(tmpCount).getWeight());
                        tmpCount++;
                    }
                }

                // 最后的前件表达式可信度 x 规则强度得到后件结论的可信度
                Double ansNum = ansStack.pop() * backPattern.getWeight();
                backPattern.setWeight(ansNum);
                // 并且将其加入到动态库当中
                subPatterns.add(backPattern);
                // 加入到结果集中
                ansPatterns.add(backPattern);
            }
        }

        return ansPatterns;
    }
}
