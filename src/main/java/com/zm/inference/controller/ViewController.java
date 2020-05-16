package com.zm.inference.controller;

import com.zm.inference.service.UserService;
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
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private UserService userService;

    @GetMapping("/goLogin")
    public String goLogin(Model model) {
        model.addAttribute("name", "周淼");
        return "login";
    }

    @GetMapping("/goSystem")
    public String goSystem(
            Model model,
            HttpServletRequest request) {
        // 在model中添加PlusUser对象
        model.addAttribute("plusUser", request.getAttribute("plusUser"));
        return "system";
    }
}
