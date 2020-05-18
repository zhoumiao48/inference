package com.zm.inference.domain;

import java.util.Date;

import com.zm.inference.common.util.domain.BaseClass;
import lombok.Data;

/**
 * 规则知识表
 */
@Data
public class Rule extends BaseClass {
    /**
     * 规则主键id
     */
    private Integer id;

    /**
     * 规则内容(中缀表达式)
     */
    private String iText;

    /**
     * 规则内容(后缀表达式)
     */
    private String rText;

    /**
     * 规则优先级
     */
    private Double priority;
}