package com.club.subject.domain.convert;

import com.club.subject.domain.entity.SubjectAnswerBO;
import com.club.subject.domain.entity.SubjectLabelBO;
import com.club.subject.infra.basic.entity.SubjectAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
@Mapper // mapStruct的注解
public interface SubjectAnswerConverter {

    SubjectAnswerConverter INSTANCE = Mappers.getMapper(SubjectAnswerConverter.class);

    SubjectAnswer convertBoToAnswer(SubjectAnswerBO subjectAnswerBO);
    List<SubjectAnswer> convertBoListToAnswerList(List<SubjectAnswerBO> subjectAnswerBOList);



}
