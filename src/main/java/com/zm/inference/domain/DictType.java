package com.zm.inference.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zm.inference.common.util.domain.BaseClass;
import lombok.Data;

/**
    * 字典项类型表
    */
@Data
public class DictType extends BaseClass {
    /**
    * 字典项类型主键id
    */
    private Integer id;

    /**
    * 字典项类型名称
    */
    @JsonProperty("tName")
    private String tName;
}