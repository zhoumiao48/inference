package com.zm.inference.domain;

import com.zm.inference.common.util.domain.BaseClass;
import lombok.Data;

/**
 * 模式表
 */
@Data
public class Pattern extends BaseClass {
    /**
     * 主键id
     */
    protected Integer id;

    /**
     * 0：只要满足这个模式的一个fact即触发此模式，
     * 1：需要满足这个模式的所有fact才能触发此模式
     */
    protected Byte isMulti;
}