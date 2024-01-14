package com.club.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.club.subject.application.converter.SubjecAnswerDTOConverter;
import com.club.subject.application.converter.SubjectCategoryDTOConverter;
import com.club.subject.application.converter.SubjectInfoDTOConverter;
import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.application.dto.SubjectInfoDTO;
import com.club.subject.common.entity.PageInfo;
import com.club.subject.common.entity.PageResult;
import com.club.subject.common.entity.Result;
import com.club.subject.domain.entity.SubjectAnswerBO;
import com.club.subject.domain.entity.SubjectCategoryBO;
import com.club.subject.domain.entity.SubjectInfoBO;
import com.club.subject.domain.service.SubjectInfoDomainService;
import com.club.subject.infra.basic.entity.SubjectCategory;
import com.club.subject.infra.basic.service.SubjectCategoryService;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.util.Collections;
import java.util.List;

/**
 * 刷题模块controller
 *
 * @author serendipity
 * @date 2024/1/11
 * @version 1.0
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class SubjectController {

    private final SubjectInfoDomainService subjectInfoDomainService;

    /**
     * 新增题目
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/add")
    public Result addCategory(@RequestBody SubjectInfoDTO subjectInfoDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.add.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }

            // 使用 guava 对入参进行校验
            Preconditions.checkArgument(!StringUtils.isAllBlank(subjectInfoDTO.getSubjectName()), "题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "分数不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()), "题目类型不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()), "题目标签不能为空");


            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDtoToInfoBo(subjectInfoDTO);
            List<SubjectAnswerBO> subjectAnswerBOList = SubjecAnswerDTOConverter.INSTANCE.convertListDtoToAnswerBo(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionList(subjectAnswerBOList);
            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok(true);

        } catch (Exception e) {
            log.error("SubjectController.add.err:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 列表题目查询
     *
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/getSubjectPage")
    public Result<PageResult<SubjectInfoDTO>> getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.getSubjectPage.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }

            // 使用 guava 对入参进行校验
            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(), "分类id不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(), "标签id不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDtoToInfoBo(subjectInfoDTO);
            PageResult<SubjectInfoBO> boList = subjectInfoDomainService.getSubjectPage(subjectInfoBO);
            return Result.ok(result);

        } catch (Exception e) {
            log.error("SubjectController.add.err:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }
}
