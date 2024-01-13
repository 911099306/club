package com.club.subject.application.controller;

import com.club.subject.infra.basic.entity.SubjectCategory;
import com.club.subject.infra.basic.service.SubjectCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 刷题模块controller
 *
 * @author serendipity
 * @date 2024/1/11
 * @version 1.0
 **/
@RestController
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectCategoryService subjectCategoryService;

    @GetMapping("/hello")
    public String test() {
        SubjectCategory subjectCategory = subjectCategoryService.queryById(1L);
        return subjectCategory.getCategoryName();
    }

}
