package com.club.oss.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.club.oss.adapter.AliStorageAdapter;
import com.club.oss.adapter.MinioStorageAdapter;
import com.club.oss.adapter.StorageAdapter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件存储config
 *
 * @author: ChickenWing
 * @date: 2023/10/14
 */
@Slf4j
@Configuration
@RefreshScope
public class StorageConfig {

    @Value(value = "${storage.service.type}")
    private String storageType;

    @Bean
    @RefreshScope
    public StorageAdapter storageService() {
        log.info("---------------------------------------------------------------");
        log.info("storageType:{}",storageType);
        log.info("---------------------------------------------------------------");
        if ("minio".equals(storageType)) {
            return new MinioStorageAdapter();
        } else if ("aliyun".equals(storageType)) {
            return new AliStorageAdapter();
        } else {
            throw new IllegalArgumentException("未找到对应的文件存储处理器");
        }
    }

}
