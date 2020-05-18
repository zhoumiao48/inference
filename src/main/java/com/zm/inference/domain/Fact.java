package com.zm.inference.domain;

import java.util.Date;

import com.zm.inference.common.util.domain.BaseClass;
import lombok.Data;

/**
 * 事实知识表
 */
@Data
public class Fact extends BaseClass {
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
}