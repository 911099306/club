package com.club.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.club.subject.application.converter.SubjectLabelDTOConverter;
import com.club.subject.application.dto.SubjectLabelDTO;
import com.club.subject.common.entity.Result;
import com.club.subject.domain.entity.SubjectLabelBO;
import com.club.subject.domain.service.SubjectLabelDomainService;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签信息
 *
 * @author serendipity
 * @version 1.0
 * @date 2024/1/13
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/subject/label")
public class SubjectLabelController {

    private final SubjectLabelDomainService subjectLabelDomainService;

    @PostMapping("/add")
    public Result addLabel(@RequestBody SubjectLabelDTO subjectLabelDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }

            // 使用 guava 对入参进行校验
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "标签分类类型id不能为空");
            Preconditions.checkArgument(!StringUtils.isAllBlank(subjectLabelDTO.getLabelName()), "标签名称不能为空");
            Preconditions.checkNotNull(subjectLabelDTO.getSortNum(), "标签分类级别不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBo(subjectLabelDTO);
            Boolean add = subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(add);
        } catch (Exception e) {
            log.error("SubjectLabelController.add.err:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result updateLabel(@RequestBody SubjectLabelDTO subjectLabelDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.updateLabel.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }

            // 使用 guava 对入参进行校验
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签id不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBo(subjectLabelDTO);
            Boolean add = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(add);
        } catch (Exception e) {
            log.error("SubjectLabelController.updateLabel.err:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.delete.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }

            Preconditions.checkNotNull(subjectLabelDTO.getId(), "删除标签id不可为空");

            SubjectLabelBO subjectLabelBo = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBo(subjectLabelDTO);

            Boolean isSuccess = subjectLabelDomainService.delete(subjectLabelBo);
            return Result.ok(isSuccess);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.err:{}", e.getMessage(), e);
            return Result.fail("删除失败");
        }

    }

    /**
     * 查询分类下标签
     */
    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelByCategoryId.dto:{}",
                        JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "分类id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBo(subjectLabelDTO);
            List<SubjectLabelBO> resultList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> subjectLabelDTOS = SubjectLabelDTOConverter.INSTANCE.convertBoListToLabelDtoLIst(resultList);
            return Result.ok(subjectLabelDTOS);
        } catch (Exception e) {
            log.error("SubjectLabelController.queryLabelByCategoryId.error:{}", e.getMessage(), e);
            return Result.fail("查询分类下标签失败");
        }
    }
}
