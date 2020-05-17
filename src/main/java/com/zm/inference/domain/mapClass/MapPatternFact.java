package com.zm.inference.domain.mapClass;

import java.util.Date;
import lombok.Data;

/**
    * 模式事实关联表
    */
@Data
public class MapPatternFact {
    /**
    * 主键id
    */
    private Integer id;

    /**
    * pattern的主键id
    */
    private Integer pId;

    /**
    * fact的主键id
    */
    private Integer fId;

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