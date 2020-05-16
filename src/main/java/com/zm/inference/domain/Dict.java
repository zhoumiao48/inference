package com.zm.inference.domain;

import java.util.Date;
import lombok.Data;

/**
 * 系统字典表
 */
@Data
public class Dict {
    private Integer id;

    /**
     * 字典项名称
     */
    private String dName;

    /**
     * 字典项类型id from dict_type表的主键
     */
    private Integer typeId;

    /**
     * 优先级
     */
    private Integer priority;

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