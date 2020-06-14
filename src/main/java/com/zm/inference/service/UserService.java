package com.zm.inference.service;

import com.zm.inference.common.util.Md5Trans;
import com.zm.inference.common.util.domain.IdAndName;
import com.zm.inference.domain.*;
import com.zm.inference.domain.mapClass.MapUserRole;
import com.zm.inference.domain.plusClass.PlusUser;
import com.zm.inference.mapper.DictMapper;
import com.zm.inference.mapper.DictTypeMapper;
import com.zm.inference.mapper.MapUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zm.inference.mapper.UserMapper;

import java.beans.Transient;
import java.util.*;

@Service
@Slf4j
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
    public boolean register(String username, String password, Integer roleId) {
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
    public PlusUser login(String username, String password, HttpServletRequest request) {
        List<User> users = userMapper.selectByUName(username);
        if (users.size() == 1) {
            User userExist = users.get(0);

            try {
                if (username.equals(userExist.getUName()) &&
                        Md5Trans.getMD5(password).equals(userExist.getUPassword())) {
                    // 加入用户角色
                    PlusUser plusUser = getUserRole(userExist);
                    request.getSession().setAttribute("plusUser", plusUser);
                    return plusUser;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 用户登出
     */
    public boolean logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("plusUser");
        Object userInfo = session.getAttribute("plusUser");
        return userInfo == null;
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
     * 获取用户对应的角色，并将结果封装为PlusUser
     */
    public PlusUser getUserRole(User user) {
        List<Integer> roleIds = mapUserRoleMapper.selectRIdByUId(user.getId());
        List<Dict> roles = dictMapper.selectByIds(roleIds);

        log.debug("roles.size(): " + roles.size());

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
            // 有多个角色，选择优先级最高的那个角色（数字越小优先级越高）
            int pivot = 0;
            for (int i = 1; i < roles.size(); i++) {
                if (roles.get(i).getPriority() < roles.get(pivot).getPriority()) {
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

    /**
     * 查看当前用户名的用户是否存在
     */
    public boolean isUserExist(User user) {
        if (userMapper.countByUName(user.getUName()) == 0) {
            return false;
        }
        return true;
    }

    /**
     * 更新用户信息并返回前端更新后的PlusUser
     */
    public PlusUser updateUserInfo(User user) {
        try {
            String md5Pwd = Md5Trans.getMD5(user.getUPassword());
            user.setUPassword(md5Pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userMapper.updateByPrimaryKeySelective(user);
        return getUserRole(user);
    }

    /**获取所有的系统用户和其对应的角色*/
    public List<PlusUser> getAllUser(){
        List<User> users = userMapper.selectAll();

        List<PlusUser> plusUsers = new ArrayList<>(users.size());

        for(User tmpUser:users){
            plusUsers.add(getUserRole(tmpUser));
        }

        return plusUsers;
    }
}
