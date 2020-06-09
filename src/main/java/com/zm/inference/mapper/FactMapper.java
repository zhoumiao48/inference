package com.zm.inference.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.zm.inference.domain.Fact;

public interface FactMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Fact record);

    /**
     * 插入后返回的是主键
     */
    int insertSelective(Fact record);

    Fact selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Fact record);

    int updateByPrimaryKey(Fact record);

    List<Integer> selectIdByFAttributeAndFValue(
            @Param("fAttribute") String fAttribute,
            @Param("fValue") String fValue);

    /**
     * 根据id集合查找返回Fact集合
     */
    List<Fact> selectByIdIn(@Param("idCollection") Collection<Integer> idCollection);

    /**
     * 根据patternId返回对应的事实知识
     */
    List<Fact> selectByPatternId(Integer patternId);
}