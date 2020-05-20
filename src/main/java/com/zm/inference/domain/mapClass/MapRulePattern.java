package com.zm.inference.domain.mapClass;

import java.util.Date;
import lombok.Data;

/**
 * 规则-模式关联表
 */
@Data
public class MapRulePattern {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 规则主键id
     */
    private Integer rId;

    /**
     * 模式主键id
     */
    private Integer pId;

    /**
     * 0代表是前件，1代表是后件
     */
    private Byte isFront;

    /**
     * 模式的权重
     */
    private Double weight;

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