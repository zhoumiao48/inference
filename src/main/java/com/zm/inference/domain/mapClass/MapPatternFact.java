package com.zm.inference.domain.mapClass;

import com.zm.inference.common.util.domain.BaseClass;
import lombok.Data;

/**
 * 模式事实关联表
 */
@Data
public class MapPatternFact extends BaseClass {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * pattern的主键id
     */
    private Integer pId;

    /**
     * fact的主键id
     */
    private Integer fId;
}