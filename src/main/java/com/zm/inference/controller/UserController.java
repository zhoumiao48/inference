package com.zm.inference.controller;

import com.zm.inference.common.util.domain.BaseController;
import com.zm.inference.common.util.MsgType;
import com.zm.inference.common.util.domain.IdAndName;
import com.zm.inference.domain.User;
import com.zm.inference.domain.plusClass.PlusUser;
import com.zm.inference.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 用户操作相关的控制器
 * @Author zm
 * @Date 2020/5/15 10:11
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;


    @PostMapping("/getRoleList")
    public Object getRoleList() {
        List<IdAndName> roleList = userService.getRoleList();
        return retMsg.Set(MsgType.SUCCESS, roleList, "返回角色列表成功");
    }

    @PostMapping("/register")
    public Object register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("roleId") Integer roleId
    ) {
        if (userService.register(username, password, roleId)) {
            return retMsg.Set(MsgType.SUCCESS, null, "注册成功,请登录");
        }
        return retMsg.Set(MsgType.ERROR, null, "用户名重复，请更换");
    }


    @PostMapping("/login")
    public Object login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request
    ) {
        PlusUser plusUser = userService.login(username, password, request);
        if (plusUser != null) {
            return retMsg.Set(MsgType.SUCCESS, plusUser, "登录成功");
        }
        return retMsg.Set(MsgType.ERROR, null, "用户名或密码错误");
    }

    @GetMapping("/logout")
    public Object logout(HttpServletRequest request) {
        if (userService.logout(request)) {
            return retMsg.Set(MsgType.SUCCESS, null, "登出成功");
        }
        return retMsg.Set(MsgType.ERROR, null, "登出失败");
    }


    @PostMapping("/updateUserInfo")
    public Object updateUserInfo(
            @RequestBody User user
    ) {
        if (userService.isUserExist(user)) {
            return retMsg.Set(MsgType.ERROR, null, "用户名已存在，请更换用户名");
        }
        PlusUser plusUser = userService.updateUserInfo(user);
        return retMsg.Set(MsgType.SUCCESS, plusUser, "用户信息更新成功");
    }

    @PostMapping("/addNewUser")
    public Object addNewUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("roleId") Integer roleId
    ) {
        User newUser = new User(username, password);

        log.debug(newUser.toString());
        if (userService.isUserExist(newUser)) {
            return retMsg.Set(MsgType.ERROR, null, "该用户名已被使用");
        }

        if (userService.register(username, password, roleId)) {
            return retMsg.Set(MsgType.SUCCESS, null, "新用户添加成功");
        }

        return retMsg.Set(MsgType.ERROR, null, "新用户添加失败");

    }

    @PostMapping("/getUserList")
    public Object getUserList() {
        System.out.println("--------");
        List<PlusUser> plusUserList = userService.getAllUser();
        return retMsg.Set(MsgType.SUCCESS, plusUserList, "获取用户集合成功");
    }
}
