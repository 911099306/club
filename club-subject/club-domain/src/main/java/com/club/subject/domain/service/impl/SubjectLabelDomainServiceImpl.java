package com.club.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.club.subject.common.enums.IsDeletedFlagEnum;
import com.club.subject.domain.convert.SubjectCategoryConverter;
import com.club.subject.domain.convert.SubjectLabelConverter;
import com.club.subject.domain.entity.SubjectLabelBO;
import com.club.subject.domain.service.SubjectLabelDomainService;
import com.club.subject.infra.basic.entity.SubjectCategory;
import com.club.subject.infra.basic.entity.SubjectLabel;
import com.club.subject.infra.basic.entity.SubjectMapping;
import com.club.subject.infra.basic.service.SubjectLabelService;
import com.club.subject.infra.basic.service.SubjectMappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/13
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    private final SubjectLabelService subjectLabelService;
    private final SubjectMappingService subjectMappingService;
    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        int count = subjectLabelService.insert(subjectLabel);
        return count > 0;
    }

    /**
     * 更新标签信息
     * @param subjectLabelBO
     * @return
     */
    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBo) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBo);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    /**
     * 查询分类下标签
     *
     * @param subjectLabelBO
     * @return
     */
    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping =SubjectMapping.builder().build();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        // 根据categoryId，在 SubjectMapping 表下，获取所有的label 的id 信息
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelIds(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return Collections.emptyList();
        }
        // 获取的label的id
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());

        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);

        List<SubjectLabelBO> boList = new LinkedList<>();
        labelList.stream().forEach(label->{
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setId(label.getId());
            bo.setLabelName(label.getLabelName());
            bo.setSortNum(label.getSortNum());
            bo.setCategoryId(label.getCategoryId());
            boList.add(bo);
        });

        return boList;
    }
}
