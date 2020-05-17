package com.zm.inference.domain.mapClass;

import java.util.Date;
import lombok.Data;

/**
    * 用户-角色关联表
    */
@Data
public class MapUserRole {
    /**
    * 主键id
    */
    private Integer id;

    /**
    * 用户id
    */
    private Integer uId;

    /**
    * 角色id from dict表
    */
    private Integer rId;

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