package com.zm.inference.mapper;

import com.zm.inference.common.util.domain.IdAndName;
import com.zm.inference.common.util.domain.RidAndPid;
import com.zm.inference.domain.mapClass.MapRulePattern;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface MapRulePatternMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapRulePattern record);

    int insertSelective(MapRulePattern record);

    MapRulePattern selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MapRulePattern record);

    int updateByPrimaryKey(MapRulePattern record);

    int insertList(@Param("list") List<MapRulePattern> list);

    List<Integer> selectDistinctRIdByPIdIn(@Param("pIdCollection") Collection<Integer> pIdCollection);

    /**
     * 返回map_rule_pattern表中的每一项（要求是前件对应关系）
     */
    List<RidAndPid> selectRIdAndPId();
}