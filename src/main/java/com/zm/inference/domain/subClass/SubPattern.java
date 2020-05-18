package com.zm.inference.domain.subClass;

import com.zm.inference.domain.Fact;
import com.zm.inference.domain.Pattern;
import lombok.Data;

import java.util.List;

/**
 * @Description Pattern类的子类
 * @Author zm
 * @Date 2020/5/17 23:00
 **/
@Data
public class SubPattern extends Pattern {
    /**
     * 模式对应的事实
     */
    private List<Fact> factList;
}
