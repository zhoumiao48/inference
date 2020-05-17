package com.zm.inference.domain;

import java.util.Date;
import lombok.Data;

/**
    * 模式表
    */
@Data
public class Pattern {
    /**
    * 主键id
    */
    private Integer id;

    /**
    * 模式的权值（数值越小权值越大）
    */
    private Integer priority;

    /**
    * 0：该pattern对应单个fact
；
1：该pattern对应多个fact
    */
    private Byte isMulti;

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