package com.itranlin.basic.common.config.mybatis;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * mybatis-plus配置
 *
 * @author itranlin
 */
@Configuration
@Slf4j
@MapperScan("com.itranlin.basic.*.mapper")
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor page = new MybatisPlusInterceptor();
        page.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return page;
    }
}
