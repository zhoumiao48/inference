package com.zm.inference.domain.mapClass;

import java.util.Date;

import com.zm.inference.common.util.domain.BaseClass;
import lombok.Data;

/**
 * 规则-模式关联表
 */
@Data
public class MapRulePattern extends BaseClass {
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
}