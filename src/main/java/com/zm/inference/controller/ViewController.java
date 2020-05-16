package com.zm.inference.controller;

import com.zm.inference.domain.PlusUser;
import com.zm.inference.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 视图控制器
 * @Author zm
 * @Date 2020/5/14 15:56
 **/
@Controller
@Slf4j
@RequestMapping("/view")
public class ViewController {

    /**
     * 登录成功后跳转到系统首页
     */
    @GetMapping("/goSystem")
    public String goSystem(
            Model model,
            HttpServletRequest request) {
        // 在model中添加PlusUser对象
        PlusUser pu = (PlusUser) request.getSession().getAttribute("plusUser");
        model.addAttribute("plusUser", pu);
        return "system";
    }

    /**
     * 跳转到登录注册页
     */
    @GetMapping("/goIndex")
    public String goIndex() {
        return "index";
    }

    @GetMapping("/goUserCenter")
    public String goUserCenter(
            Model model,
            HttpServletRequest request
    ){
        // 在model中添加PlusUser对象
        PlusUser pu = (PlusUser) request.getSession().getAttribute("plusUser");
        model.addAttribute("plusUser", pu);
        return "userCenter";
    }
}
