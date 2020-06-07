package com.zm.inference.controller;

import com.zm.inference.common.util.MsgType;
import com.zm.inference.common.util.domain.BaseController;
import com.zm.inference.domain.subClass.SubPattern;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 推理控制器
 * @Author zm
 * @Date 2020/5/20 9:55
 **/

@RestController
@RequestMapping("/inference")
public class InferenceController extends BaseController {


    @PostMapping("/doInference")
    public Object doInference(@RequestBody List<SubPattern> subPatternList) {
        // 前端传入的subPatternList有的id和weight

        // 根据pattern_id去找有没有匹配了的规则 -> 冲突消解 -> 规则激活

        // 返回前端一个事实集合
        return retMsg.Set(MsgType.SUCCESS, subPatternList, "推理成功");
    }
}
