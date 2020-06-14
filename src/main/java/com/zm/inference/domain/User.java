package com.zm.inference.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("uName")
    protected String uName;

    /**
     * 用户密码
     */
    @JsonProperty("uPassword")
    protected String uPassword;

    public User() {
    }

    public User(String uName, String uPassword) {
        this.uName = uName;
        this.uPassword = uPassword;
    }
}