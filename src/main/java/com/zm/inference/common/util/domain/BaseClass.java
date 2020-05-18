package com.zm.inference.common.util.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description 封装了一些共有属性和共有操作的基类
 * @Author zm
 * @Date 2020/5/17 23:12
 **/
@Data
public abstract class BaseClass {

    /**
     * 创建时间
     */
    protected Date createdTime;

    /**
     * 修改时间
     */
    protected Date modifiedTime;

    /**
     * 创建人id
     */
    protected Integer createdUid;

    /**
     * 修改人id
     */
    protected Integer modifiedUid;


    /**
     * 默认插入创建时间和修改时间
     */
    public BaseClass() {
        Date dateNow = new Date();
        this.createdTime = dateNow;
        this.modifiedTime = dateNow;
    }
}
