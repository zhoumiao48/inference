package com.zm.inference.domain;

import java.util.Date;

import lombok.Data;

/**
 * 系统用户表
 */
@Data
public class User {
    /**
     * 用户主键id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String uName;

    /**
     * 用户密码
     */
    private String uPassword;

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