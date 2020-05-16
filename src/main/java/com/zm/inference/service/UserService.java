package com.zm.inference.service;

import com.zm.inference.common.util.Md5Trans;
import com.zm.inference.common.util.domain.IdAndName;
import com.zm.inference.domain.*;
import com.zm.inference.mapper.DictMapper;
import com.zm.inference.mapper.DictTypeMapper;
import com.zm.inference.mapper.MapUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zm.inference.mapper.UserMapper;

import java.beans.Transient;
import java.util.*;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MapUserRoleMapper mapUserRoleMapper;

    @Resource
    private DictMapper dictMapper;

    @Resource
    private DictTypeMapper dictTypeMapper;

    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }


    public int insert(User record) {
        return userMapper.insert(record);
    }


    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }


    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     * 新用户注册
     */
    @Transient
    public boolean register(String username, String password,Integer roleId) {
        int userCount = userMapper.selectByUName(username).size();
        if (userCount != 0) {
            return false;
        }

        Date dateNow = new Date();
        User newUser = new User();
        newUser.setUName(username);
        try {
            newUser.setUPassword(Md5Trans.getMD5(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        newUser.setCreatedTime(dateNow);
        newUser.setModifiedTime(dateNow);

        if (userMapper.insert(newUser) == 1) {
            User insertedUser = userMapper.selectByUName(username).get(0);
            MapUserRole mapUserRole = new MapUserRole();
            mapUserRole.setUId(insertedUser.getId());
            mapUserRole.setRId(roleId);
            mapUserRole.setCreatedTime(dateNow);
            mapUserRole.setModifiedTime(dateNow);
            mapUserRoleMapper.insertSelective(mapUserRole);
            return true;
        }

        return false;
    }

    /**
     * 用户登录
     */
    public boolean login(String username, String password, HttpServletRequest request) {
        List<User> users = userMapper.selectByUName(username);
        if (users.size() == 1) {
            User userExist = users.get(0);

            try {
                if (username.equals(userExist.getUName()) &&
                        Md5Trans.getMD5(password).equals(userExist.getUPassword())) {
                    // 加入用户角色
                    PlusUser plusUser = getUserRole(userExist);
                    request.getSession().setAttribute("plusUser", plusUser);
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 获取系统中所有的角色
     */
    public List<IdAndName> getRoleList() {
        // 获取”用户角色“对应的主键id值
        Integer roleTypeId = dictTypeMapper.selectByTName("用户角色").get(0).getId();
        // 在dict表中根据type_id获取所有的角色id + 角色名
        return dictMapper.selectIdAndDNameByTypeId(roleTypeId);
    }

    /**
     * 获取用户对应的角色
     */
    public PlusUser getUserRole(User user) {
        List<Integer> roleIds = mapUserRoleMapper.selectRIdByUId(user.getId());
        Map<String, Object> roleIdsParamMap = new HashMap<>();
        roleIdsParamMap.put("roleIds", roleIds);
        List<Dict> roles = dictMapper.selectByIds(roleIdsParamMap);

        int size = roles.size();
        String uRole = "";
        if (size == 0) {
            try {
                throw new Exception("用户角色错误");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (size == 1) {
            // 仅有单个角色
            uRole = roles.get(0).getDName();
        } else {
            // 有多个角色，选择优先级最高的那个角色
            int pivot = 0;
            for (int i = 1; i < roles.size(); i++) {
                if (roles.get(i).getPriority() > roles.get(pivot).getPriority()) {
                    pivot = i;
                }
            }
            uRole = roles.get(pivot).getDName();
        }

        PlusUser pUser = new PlusUser();
        pUser.setUser(user);
        pUser.setRole(uRole);
        return pUser;
    }
}
