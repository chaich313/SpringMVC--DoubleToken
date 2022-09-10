package com.itranlin.reimu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author itranlin
 */
@EnableAsync
@SpringBootApplication
public class BootBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootBasicApplication.class, args);
    }

}
