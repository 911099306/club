package com.club.subject.domain.service.impl;

import com.club.subject.domain.convert.SubjectAnswerConverter;
import com.club.subject.domain.convert.SubjectInfoConverter;
import com.club.subject.domain.entity.SubjectInfoBO;
import com.club.subject.domain.handler.subject.SubjectTypeHandler;
import com.club.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.club.subject.domain.service.SubjectInfoDomainService;
import com.club.subject.infra.basic.entity.SubjectAnswer;
import com.club.subject.infra.basic.entity.SubjectInfo;
import com.club.subject.infra.basic.entity.SubjectMapping;
import com.club.subject.infra.basic.service.SubjectInfoService;
import com.club.subject.infra.basic.service.SubjectMappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/13
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    private final SubjectInfoService subjectInfoService;
    private final SubjectMappingService subjectMappingService;

    private final SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    /**
     * 新增题目
     *
     * @param subjectInfoBO 实例对象
     * @return
     */
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", subjectInfoBO);
        }


        // 插入题目元数据信息
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfoService.insert(subjectInfo);

        // 插入提题目具体信息
        /*  假设我们都写在 主流程 里
        判断 type， 单选的调用单选的Service，多选的调用多选的Service
        一大堆的if
        使用一个 工厂+策略 的形式
        一个工厂包含 4中类型，根据传入的type自动映射选择处理   */
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        handler.add(subjectInfoBO);

        // 插入 mapping 表信息

        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> subjectMappingList = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId->{
                SubjectMapping subjectMapping = SubjectMapping.builder()
                        .subjectId(subjectInfo.getId())
                        .categoryId(Long.valueOf(categoryId))
                        .labelId(Long.valueOf(labelId))
                        .build();
                subjectMappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(subjectMappingList);


    }
}
