package com.zm.inference.domain;

import java.util.Date;
import lombok.Data;

/**
    * 事实知识表
    */
@Data
public class Fact {
    /**
    * 事实知识主键id
    */
    private Integer id;

    /**
    * 对象属性
    */
    private String fAttribute;

    /**
    * 属性值
    */
    private String fValue;

    /**
    * 可信度or程度
    */
    private Double fDegree;

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