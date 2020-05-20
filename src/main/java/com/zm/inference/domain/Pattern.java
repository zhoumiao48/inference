package com.zm.inference.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
     * 0：该pattern对应单个fact
     * 1：该pattern对应多个fact
     */
    protected Byte isMulti;
}