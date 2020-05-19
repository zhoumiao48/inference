package com.zm.inference.controller;

import com.zm.inference.common.util.domain.BaseController;
import com.zm.inference.common.util.MsgType;
import com.zm.inference.domain.Fact;
import com.zm.inference.domain.Pattern;
import com.zm.inference.domain.Rule;
import com.zm.inference.domain.User;
import com.zm.inference.domain.subClass.SubPattern;
import com.zm.inference.domain.subClass.SubRule;
import com.zm.inference.service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/5/13 18:14
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController extends BaseController {


    @Autowired
    private RuleService ruleService;

    @GetMapping("/hello")
    public String getIndex(){

        log.info("我是一条info信息");
        return "hello zm^^";
    }

    @GetMapping("/getRule")
    public Object getRule(
            @RequestParam("id") String id
    ){
        Integer iid = Integer.valueOf(id);
        Rule resRule = ruleService.selectByPrimaryKey(iid);
        return retMsg.Set(MsgType.SUCCESS,resRule,"根据id获取rule成功");
    }

    @GetMapping("goLogin")
    public String goLogin(){
        return "login";
    }

    @PostMapping("/testResponseBody")
    public Object testResponseBody(@RequestBody SubRule sp){

        System.out.println(sp);
        return retMsg.Set(MsgType.SUCCESS,sp,"成功");
    }
}
