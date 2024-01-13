package com.club.subject.domain.convert;

import com.club.subject.domain.entity.SubjectCategoryBO;
import com.club.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
@Mapper // mapStruct的注解
public interface SubjectCategoryConverter {

    SubjectCategoryConverter INSTANCE = Mappers.getMapper(SubjectCategoryConverter.class);

    SubjectCategory convertBoToCategory(SubjectCategoryBO subjectCategoryBO);
    List<SubjectCategoryBO> convertToCategoryBoList(List<SubjectCategory> subjectCategoryList);


}
