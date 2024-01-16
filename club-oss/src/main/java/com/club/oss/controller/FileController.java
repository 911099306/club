package com.club.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.club.oss.service.FileService;
import com.club.oss.util.MinioUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/15
 **/
@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @GetMapping("/testGetAllBucket")
    public String testGetAllBucket() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }


}
