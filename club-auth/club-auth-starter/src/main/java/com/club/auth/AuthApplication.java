package com.club.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/16
 **/
@SpringBootApplication
@ComponentScan("com.club")
@MapperScan("com.club.**.mapper")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
