package com.zm.inference.common.util.domain;

import lombok.Data;

/**
 * @Description mybatis返回类：Integer + String类型
 * @Author zm
 * @Date 2020/5/16 12:05
 **/
@Data
public class IdAndName {
    private Integer id;
    private String name;
}
