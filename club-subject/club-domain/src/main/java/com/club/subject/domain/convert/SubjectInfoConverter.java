package com.club.subject.domain.convert;

import com.club.subject.domain.entity.SubjectInfoBO;
import com.club.subject.domain.entity.SubjectLabelBO;
import com.club.subject.infra.basic.entity.SubjectInfo;
import com.club.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
@Mapper // mapStruct的注解
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBoToInfo(SubjectInfoBO subjectInfoBO);


}