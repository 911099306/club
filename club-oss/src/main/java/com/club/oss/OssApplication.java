package com.club.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 * @author serendipity
 */
@SpringBootApplication
@ComponentScan("com.club")
public class OssApplication {
    public static void main( String[] args ) {
        SpringApplication.run(OssApplication.class, args);
    }
}
