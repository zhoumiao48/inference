package com.zm.inference.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.zm.inference.domain.mapClass.MapUserRole;

public interface MapUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapUserRole record);

    int insertSelective(MapUserRole record);

    MapUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MapUserRole record);

    int updateByPrimaryKey(MapUserRole record);

    /**
     * 根据userID获取对应的角色
     */
    List<Integer> selectRIdByUId(@Param("uId")Integer uId);
}