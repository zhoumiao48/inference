package com.zm.inference.controller;

import com.zm.inference.common.util.MsgType;
import com.zm.inference.common.util.domain.BaseController;
import com.zm.inference.domain.Fact;
import com.zm.inference.service.FactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/6/14 16:52
 **/
@RestController
@Slf4j
@RequestMapping("/inference/fact")
public class FactController extends BaseController {

    @Resource
    private FactService factService;

    @PostMapping("/getFactList")
    public Object getFactList() {
        List<Fact> factList = factService.getAllFact();
        return retMsg.Set(MsgType.SUCCESS, factList, "返回事实知识成功");
    }
}
