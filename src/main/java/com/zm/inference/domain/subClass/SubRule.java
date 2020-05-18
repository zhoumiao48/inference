package com.zm.inference.domain.subClass;

import com.zm.inference.domain.Rule;
import lombok.Data;

import java.util.List;

/**
 * @Description Rule的子类
 * @Author zm
 * @Date 2020/5/17 23:06
 **/
@Data
public class SubRule extends Rule {

    /**
     * 前件的模式
     */
    private List<SubPattern> frontPatternList;

    /**
     * 后件的模式
     */
    private List<SubPattern> backPatternList;
}
