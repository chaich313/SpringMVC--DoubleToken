package com.itranlin.reimu.common.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatMpConfig {

    /**
     * 配置WxMpService所需信息
     *
     * @return WxMpService
     */
    @Bean  // 此注解指定在Spring容器启动时，就执行该方法并将该方法返回的对象交由Spring容器管理
    public WxMpService wxMpService(WxMpConfigStorage wxMpConfigStorage) {
        WxMpService wxMpService = new WxMpServiceImpl();
        // 设置配置信息的存储位置
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
        return wxMpService;
    }

    /**
     * 配置appID和appsecret
     *
     * @return WxMpConfigStorage
     */
    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        // 使用这个实现类则表示将配置信息存储在内存中
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId("wxc47fd1e4ef9ef2af");
        wxMpDefaultConfig.setSecret("d37f0957c92177f009829498f03474c5");
        return wxMpDefaultConfig;
    }
}
