package com.zm.inference.controller;

import com.zm.inference.common.util.domain.BaseController;
import com.zm.inference.common.util.MsgType;
import com.zm.inference.common.util.domain.IdAndName;
import com.zm.inference.domain.plusClass.PlusUser;
import com.zm.inference.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 用户操作相关的控制器
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
}
