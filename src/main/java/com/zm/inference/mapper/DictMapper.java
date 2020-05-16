package com.zm.inference.mapper;
import com.zm.inference.common.util.domain.IdAndName;
import org.apache.ibatis.annotations.Param;

import com.zm.inference.domain.Dict;

import java.util.List;
import java.util.Map;

public interface DictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);

    /**
     * 根据id集合获取Dict集合
     */
    List<Dict> selectByIds(Map<String, Object> dictIds);

    /**根据type_id获取所有的d_name*/
    List<IdAndName> selectIdAndDNameByTypeId(@Param("typeId")Integer typeId);
}