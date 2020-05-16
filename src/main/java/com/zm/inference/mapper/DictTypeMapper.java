package com.zm.inference.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.zm.inference.domain.DictType;

public interface DictTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DictType record);

    int insertSelective(DictType record);

    DictType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DictType record);

    int updateByPrimaryKey(DictType record);

    /**
     * 根据TypeName获取DictType
     */
    List<DictType> selectByTName(@Param("tName") String tName);
}