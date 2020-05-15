package com.zm.inference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description 视图控制器
 * @Author zm
 * @Date 2020/5/14 15:56
 **/
@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/goLogin")
    public String goLogin(Model model){
        model.addAttribute("name","周淼");
        return "login";
    }
}
