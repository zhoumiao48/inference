package com.zm.inference.controller;

import com.zm.inference.common.util.MsgType;
import com.zm.inference.common.util.domain.BaseController;
import com.zm.inference.domain.subClass.SubPattern;
import com.zm.inference.service.InferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 推理控制器
 * @Author zm
 * @Date 2020/5/20 9:55
 **/

@Slf4j
@RestController
@RequestMapping("/inference")
public class InferenceController extends BaseController {


    @Resource
    private InferenceService inferenceService;

    @PostMapping("/doInference")
    public Object doInference(@RequestBody List<SubPattern> subPatternList) {
        List<SubPattern> ansPatterns = inferenceService.doInference(subPatternList);

        if (ansPatterns.size() == 0) {
            return retMsg.Set(MsgType.ERROR, null, "推理失败");
        }
        return retMsg.Set(MsgType.SUCCESS, ansPatterns, "推理成功");
    }
}
