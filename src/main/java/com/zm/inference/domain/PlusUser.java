package com.zm.inference.domain;

import lombok.Data;

/**
 * @Description User类的包装类
 * @Author zm
 * @Date 2020/5/16 10:51
 **/
@Data
public class PlusUser {
    private User user;

    /**
     * 用户角色
     */
    private String role;
}
