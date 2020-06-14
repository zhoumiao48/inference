package com.zm.inference.controller;

import com.sun.org.apache.xpath.internal.operations.Plus;
import com.zm.inference.domain.plusClass.PlusUser;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * 跳转到个人中心页
     */
    @GetMapping("/goUserCenter")
    public String goUserCenter(
            Model model,
            HttpServletRequest request
    ) {
        // 在model中添加PlusUser对象
        PlusUser pu = (PlusUser) request.getSession().getAttribute("plusUser");
        model.addAttribute("plusUser", pu);
        return "userCenter";
    }

    /**
     * 跳转到推理页面
     */
    @GetMapping("/goInference")
    public String goInference(
            Model model,
            HttpServletRequest request
    ) {
        // 在model中添加PlusUser对象
        PlusUser pu = (PlusUser) request.getSession().getAttribute("plusUser");
        model.addAttribute("plusUser", pu);
        return "inference";
    }

    /**
     * 跳转到添加规则页面
     */
    @GetMapping("/goAddRule")
    public String goAddRule(
            Model model,
            HttpServletRequest request
    ) {
        PlusUser pu = (PlusUser) request.getSession().getAttribute("plusUser");
        model.addAttribute("plusUser", pu);
        return "addRule";
    }

    /**
     * 跳转到增加用户界面
     */
    @GetMapping("/goAddUser")
    public String goAddUser(
            Model model,
            HttpServletRequest request
    ) {
        PlusUser pu = (PlusUser) request.getSession().getAttribute("plusUser");
        model.addAttribute("plusUser", pu);
        return "addUser";
    }

    /**
     * 跳转到规则集合页面
     */
    @GetMapping("/goRuleList")
    public String goRuleList(
            Model model,
            HttpServletRequest request
    ) {
        PlusUser pu = (PlusUser) request.getSession().getAttribute("plusUser");
        model.addAttribute("plusUser", pu);
        return "ruleList";
    }

    /**
     * 跳转到事实知识集合页面
     */
    @GetMapping("/goFactList")
    public String goFactList(
            Model model,
            HttpServletRequest request
    ) {
        PlusUser pu = (PlusUser) request.getSession().getAttribute("plusUser");
        model.addAttribute("plusUser", pu);
        return "factList";
    }

    /**
     * 跳转到事实知识集合页面
     */
    @GetMapping("/goUserList")
    public String goUserList(
            Model model,
            HttpServletRequest request
    ) {
        PlusUser pu = (PlusUser) request.getSession().getAttribute("plusUser");
        model.addAttribute("plusUser", pu);
        return "userList";
    }
}
