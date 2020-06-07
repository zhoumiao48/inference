package com.zm.inference.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zm.inference.common.util.domain.BaseClass;
import lombok.Data;

/**
 * 规则知识表
 * @author zm
 */
@Data
public class Rule extends BaseClass {
    /**
     * 规则主键id
     */
    protected Integer id;

    /**
     * 规则内容(中缀表达式)
     */
    @JsonProperty("iText")
    protected String iText;

    /**
     * 规则内容(后缀表达式)
     */
    @JsonProperty("rText")
    protected String rText;

    /**
     * 规则说明（规则名称）
     */
    protected String content;

    /**
     * 规则强度（用于冲突消解时候后件模式的weight相同时候比较，选定出强度高的那条规则进行激活）
     */
    protected Double degree;
}