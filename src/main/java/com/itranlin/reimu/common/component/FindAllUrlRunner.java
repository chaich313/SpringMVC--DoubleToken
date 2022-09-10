package com.itranlin.reimu.common.component;

import com.itranlin.reimu.common.bean.BasicConfig;
import com.itranlin.reimu.common.util.JacksonUtil;
import com.itranlin.reimu.common.util.SpringUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

/**
 * @author itranlin
 * @since 2021/4/21 14:17
 */
@Slf4j
@Component
public class FindAllUrlRunner implements ApplicationRunner {

    @Resource
    private BasicConfig basicConfig;

    @Override
    public void run(ApplicationArguments args) {
        RequestMappingHandlerMapping mapping = SpringUtils.getBean(RequestMappingHandlerMapping.class);
        List<String> urls = new ArrayList<>();
        mapping.getHandlerMethods().forEach((key, value) -> {
            if (value.getBeanType().getName().startsWith(basicConfig.getBasicPackage())) {
                if (key.getPatternsCondition() != null) {
                    urls.addAll(key.getPatternsCondition().getPatterns());
                }
            }
        });
        log.debug(JacksonUtil.toString(urls));
    }
}
