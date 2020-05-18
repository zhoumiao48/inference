package com.zm.inference.domain;

import java.util.Date;

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
    private Integer id;

    /**
     * 模式的权值（数值越小权值越大）
     */
    private Float priority;

    /**
     * 0：该pattern对应单个fact
     * 1：该pattern对应多个fact
     */
    private Byte isMulti;

}