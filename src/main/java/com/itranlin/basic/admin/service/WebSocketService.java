package com.itranlin.basic.admin.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author itranlin
 * @since 2022/5/21 16:28
 */
@Service
public class WebSocketService {
    @Resource
    private SimpMessagingTemplate messagingTemplate;

    public void send(String des, Object message) {
        messagingTemplate.convertAndSend(des, message);
    }

    public void sendToUser(String userId, String des, Object message) {
        messagingTemplate.convertAndSendToUser(userId, des, message);
    }

}
