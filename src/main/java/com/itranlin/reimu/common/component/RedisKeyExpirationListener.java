package com.itranlin.reimu.common.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author itranlin
 */
@Slf4j
@Component
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.NESTED, rollbackFor = Exception.class)
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    /**
     * 针对redis数据失效事件，进行数据处理
     *
     * @param message message
     * @param pattern pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String key = message.toString();
        log.debug("redis失效----Key={}", key);
    }
}
