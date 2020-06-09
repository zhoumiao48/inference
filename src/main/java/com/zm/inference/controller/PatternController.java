package com.zm.inference.controller;

import com.zm.inference.common.util.MsgType;
import com.zm.inference.common.util.domain.BaseController;
import com.zm.inference.domain.subClass.SubPattern;
import com.zm.inference.service.PatternService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/6/8 10:22
 **/
@RestController
@Slf4j
@RequestMapping("/inference/pattern")
public class PatternController extends BaseController {

    @Resource
    private PatternService patternService;


    @PostMapping("/getPatterns")
    public Object getPatterns() {
        List<SubPattern> subPatternList = patternService.getPatternList();
        return retMsg.Set(MsgType.SUCCESS, subPatternList, "获取模式集合成功");
    }
}
