package com.zm.inference.controller;

import com.zm.inference.common.util.BaseController;
import com.zm.inference.common.util.MsgType;
import com.zm.inference.common.util.domain.IdAndName;
import com.zm.inference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/5/15 10:11
 **/
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
        if (userService.register(username, password,roleId)) {
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
        if (userService.login(username, password, request)) {
            return retMsg.Set(MsgType.SUCCESS, null, "登录成功");
        }
        return retMsg.Set(MsgType.ERROR, null, "用户名或密码错误");
    }
}
