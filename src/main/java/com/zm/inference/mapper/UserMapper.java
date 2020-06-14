package com.zm.inference.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.zm.inference.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    Integer countByUName(@Param("uName")String uName);

    List<User> selectByUName(@Param("uName")String uName);

    List<User> selectAll();


}