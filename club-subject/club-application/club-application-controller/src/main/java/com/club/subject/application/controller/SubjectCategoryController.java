package com.club.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.club.subject.application.converter.SubjectCategoryDTOConverter;
import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.common.entity.Result;
import com.club.subject.domain.convert.SubjectCategoryConverter;
import com.club.subject.domain.entity.SubjectCategoryBO;
import com.club.subject.domain.service.SubjectCategoryDomainService;
import com.club.subject.infra.basic.entity.SubjectCategory;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 刷题模块
 *
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/subject/category")
public class SubjectCategoryController {
    private final SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result addCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }

            // 使用 guava 对入参进行校验
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryName(), "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分类父级别不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE
                    .convertDtoToCategoryBo(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.err:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
        return Result.ok();
    }

    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO)  {
        try {
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);

            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE
                    .convertBoListToCategoryDtoLIst(subjectCategoryBOList);
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryPrimaryCategory.dtoList:{}", subjectCategoryDTOList);
            }
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.err:{}", e.getMessage(), e);
            return Result.fail("查询失败！");
        }

    }

    @PostMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO)  {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryPrimaryCategory.dto:{}", subjectCategoryDTO);
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);

            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不可为空！");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "父类类型不可为空！");

            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE
                    .convertBoListToCategoryDtoLIst(subjectCategoryBOList);
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryPrimaryCategory.dtoList:{}", subjectCategoryDTOList);
            }
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.err:{}", e.getMessage(), e);
            return Result.fail("查询失败！");
        }

    }



}
