package com.club.subject;

import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 刷题微服务启动类
 *
 * @author monou
 * @date 2024/1/11
 */
@SpringBootApplication
@ComponentScan("com.club")
@MapperScan("com.club.**.mapper")
public class SubjectApplication {
    public static void main( String[] args ) {
        SpringApplication.run(SubjectApplication.class);
    }
}
