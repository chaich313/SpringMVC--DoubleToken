package com.itranlin.reimu.admin.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author itranlin
 * @since 2022/6/1 20:39
 */
@Service
@Async
public class ScriptService {
    @Resource
    private WebSocketService webSocketService;

}
