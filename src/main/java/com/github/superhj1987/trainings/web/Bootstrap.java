package com.github.superhj1987.trainings.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Author: Bryant.Hang
 * Date: 2018/9/3
 * Email: superhj1987@126.com
 */
@SpringBootApplication
public class Bootstrap extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    public static void main(String[] args) {
//		System.setProperty("server.port","9000");
        SpringApplication.run(Bootstrap.class, args);
    }
}
