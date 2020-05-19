package com.zm.inference.domain;

import java.util.Date;

import com.zm.inference.common.util.domain.BaseClass;
import lombok.Data;

/**
 * 系统用户表
 */
@Data
public class User extends BaseClass {
    /**
     * 用户主键id
     */
    protected Integer id;

    /**
     * 用户名
     */
    protected String uName;

    /**
     * 用户密码
     */
    protected String uPassword;
}