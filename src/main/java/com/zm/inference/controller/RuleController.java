package com.zm.inference.controller;

import com.zm.inference.common.util.domain.BaseController;
import com.zm.inference.common.util.MsgType;
import com.zm.inference.domain.plusClass.PlusUser;
import com.zm.inference.domain.subClass.SubRule;
import com.zm.inference.service.RuleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 规则控制器
 * @Author zm
 * @Date 2020/5/17 11:31
 **/
@RestController
@RequestMapping("/inference/rule")
public class RuleController extends BaseController {


    @Resource
    private RuleService ruleService;

    @PostMapping("/addRule")
    public Object addNewRule(@RequestBody SubRule subRule) {
        if (ruleService.addNewRule(subRule)) {
            return retMsg.Set(MsgType.SUCCESS, null, "规则录入成功");
        }
        return retMsg.Set(MsgType.ERROR, null, "规则知识录入失败");
    }
}