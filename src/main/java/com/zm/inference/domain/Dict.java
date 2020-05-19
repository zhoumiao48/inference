package com.zm.inference.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zm.inference.common.util.domain.BaseClass;
import lombok.Data;

/**
 * 系统字典表
 */
@Data
public class Dict extends BaseClass {
    private Integer id;

    /**
     * 字典项名称
     */
    @JsonProperty("dName")
    private String dName;

    /**
     * 字典项类型id from dict_type表的主键
     */
    private Integer typeId;

    /**
     * 优先级
     */
    private Integer priority;
}