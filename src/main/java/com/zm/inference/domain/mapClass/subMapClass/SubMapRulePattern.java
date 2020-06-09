package com.zm.inference.domain.mapClass.subMapClass;

import com.zm.inference.domain.mapClass.MapRulePattern;
import com.zm.inference.domain.subClass.SubPattern;

import java.util.List;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/6/9 10:32
 **/
public class SubMapRulePattern extends MapRulePattern {

    /**
     * 规则前件模式
     */
    private List<SubPattern> frontPatterns;

    /**
     * 规则后件模式
     */
    private List<SubPattern> backPatterns;
}
