package com.zm.inference.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
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
     * 规则强度
     */
    protected Double degree;
}