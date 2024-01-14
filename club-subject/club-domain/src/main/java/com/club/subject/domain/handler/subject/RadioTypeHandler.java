package com.club.subject.domain.handler.subject;

import com.club.subject.common.enums.SubjectInfoTypeEnum;
import com.club.subject.domain.convert.SubjectRadioConverter;
import com.club.subject.domain.entity.SubjectInfoBO;
import com.club.subject.infra.basic.entity.SubjectRadio;
import com.club.subject.infra.basic.service.SubjectRadioService;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * 单选的策略类
 *
 * @author serendipity
 * @version 1.0
 * @date 2024/1/13
 **/
@RequiredArgsConstructor
public class RadioTypeHandler implements SubjectTypeHandler {

    private final SubjectRadioService subjectRadioService;
    @Override

    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // 单选题目的插入
        // 增加一些校验的东西
        List<SubjectRadio> subjectRadioList = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectRadio subjectRadio = SubjectRadioConverter.INSTANCE.convertBoToEntity(option);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadioList.add(subjectRadio);
        });

        subjectRadioService.batchInsert(subjectRadioList);
    }
}
