package com.zm.inference.domain;

import java.util.Date;
import lombok.Data;

/**
    * 规则知识表
    */
@Data
public class Rule {
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

    /**
    * 创建时间
    */
    private Date createdTime;

    /**
    * 修改时间
    */
    private Date modifiedTime;

    /**
    * 创建人id
    */
    private Integer createdUid;

    /**
    * 修改人id
    */
    private Integer modifiedUid;
}