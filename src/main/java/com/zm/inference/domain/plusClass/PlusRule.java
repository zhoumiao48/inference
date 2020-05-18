package com.zm.inference.domain.plusClass;

import com.zm.inference.domain.Rule;
import lombok.Data;

import java.util.List;

/**
 * @Description Rule的包装类
 * @Author zm
 * @Date 2020/5/17 10:04
 **/
@Data
public class PlusRule {

    /**
     * 规则本身
     */
    private Rule rule;

    /**
     * 前件的模式
     */
    private List<PlusPattern> frontPatternList;

    /**
     * 后件的模式
     */
    private List<PlusPattern> backPatternList;
}
