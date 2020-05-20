package com.zm.inference.controller;

import com.zm.inference.common.util.MsgType;
import com.zm.inference.common.util.domain.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 推理控制器
 * @Author zm
 * @Date 2020/5/20 9:55
 **/

@RestController
@RequestMapping("/inference")
public class InferenceController extends BaseController {


    @PostMapping("/doInference")
    public Object doInference() {

        return retMsg.Set(MsgType.SUCCESS, null, "ok");
    }
}
