package com.zm.inference.domain;

import java.util.Date;
import lombok.Data;

/**
    * 字典项类型表
    */
@Data
public class DictType {
    /**
    * 字典项类型主键id
    */
    private Integer id;

    /**
    * 字典项类型名称
    */
    private String tName;

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