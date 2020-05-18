package com.zm.inference.service.Interface;

import com.zm.inference.domain.subClass.SubPattern;
import com.zm.inference.domain.subClass.SubRule;

import java.util.List;

public interface RuleServiceInterface {

    /**
     * 新增规则知识，成功插入返回1，失败返回0
     *
     * @param subRule 需要插入的规则知识
     * @return 成功插入返回true，失败则返回false
     * @author zm
     * @date 2020/5/18 22:44
     **/
    Boolean addNewRule(SubRule subRule);

    /**
     * 检查数据库中是否已经存在此规则
     *
     * @param frontPatterns 规则前件中的模式list（检查是否存在只需要前件）
     * @return 若在就返回rule_id。不存在则返回-1
     * @author zm
     * @date 2020/5/18 18:22
     **/
    Integer checkRule(List<SubPattern> frontPatterns);
}
