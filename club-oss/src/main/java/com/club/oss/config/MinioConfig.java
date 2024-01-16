package com.club.oss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio 配置管理
 * @author serendipity
 * @version 1.0
 * @date 2024/1/15
 **/
@Configuration
public class MinioConfig {

    /**
     * minioUrl
     */
    @Value("${minio.url}")
    private String url;

    /**
     * minio账户
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * minio密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 构造minioClient
     */
    @Bean
    public MinioClient getMinioClient() {
        try {
            return MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while creating MinioClient", e);
        }
        // return MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
    }

}
