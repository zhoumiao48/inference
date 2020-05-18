package com.zm.inference.domain.plusClass;

import com.zm.inference.domain.Fact;
import com.zm.inference.domain.Pattern;
import lombok.Data;

import java.util.List;

/**
 * @Description Pattern类的包装类
 * @Author zm
 * @Date 2020/5/17 9:59
 **/
@Data
public class PlusPattern {
    private Pattern pattern;

    /**
     * 模式对应的事实
     */
    private List<Fact> factList;
}
