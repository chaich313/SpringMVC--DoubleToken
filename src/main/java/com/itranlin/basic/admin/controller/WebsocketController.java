package com.itranlin.basic.admin.controller;

import com.itranlin.basic.common.bean.RequestResult;
import com.itranlin.basic.common.bean.StatusEnum;
import com.itranlin.basic.common.util.script.ScriptUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author itranlin
 * @since 2022/6/1 20:50
 */
@RestController
@RequestMapping("/api/shell")
public class WebsocketController {

    @Resource
    private ScriptUtils scriptUtils;

    @GetMapping("t")
    public RequestResult<Void> exec() {
        scriptUtils.execHello();
        return RequestResult.e(StatusEnum.OK);
    }

}
