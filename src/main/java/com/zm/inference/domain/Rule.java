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
    protected Integer id;

    /**
     * 规则内容(中缀表达式)
     */
    protected String iText;

    /**
     * 规则内容(后缀表达式)
     */
    protected String rText;

    /**
     * 规则优先级
     */
    protected Double priority;

    public String getiText() {
        return iText;
    }

    public String getrText() {
        return rText;
    }

    public void setiText(String iText) {
        this.iText = iText;
    }

    public void setrText(String rText) {
        this.rText = rText;
    }
}