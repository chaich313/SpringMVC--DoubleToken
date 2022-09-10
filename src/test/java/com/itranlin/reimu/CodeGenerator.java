package com.itranlin.reimu;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author itranlin
 * @since 2022/5/9 20:16
 */
public class CodeGenerator {

    private static final String JDBC_URL = "jdbc:mysql://itranlin.com:3306/basic?characterEncoding=utf8"
                                           + "&zeroDateTimeBehavior=convertToNull&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "chen.he321231";

    private static final String[] TABLES = {"sys_customer"};

    public static void main(String[] args) {
        if (null == TABLES || TABLES.length == 0) {
            System.out.println("需要填写表名，请修改【TABLES】属性");
            return;
        }
        FastAutoGenerator.create(JDBC_URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author("itranlin") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir()
                            .outputDir("src/test/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.itranlin.reimu.admin") // 设置父包名
                            .pathInfo(
                                    Collections.singletonMap(OutputFile.xml, "src/test/resources")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLES); // 设置过滤表前缀
                    builder.controllerBuilder().enableRestStyle();
                    builder.entityBuilder().enableChainModel().enableLombok();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
