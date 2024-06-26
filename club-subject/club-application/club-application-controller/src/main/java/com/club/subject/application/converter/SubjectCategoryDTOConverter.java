package com.club.subject.application.converter;

import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
@Mapper
public interface SubjectCategoryDTOConverter {

    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertDtoToCategoryBo(SubjectCategoryDTO subjectCategoryDTO);
    List<SubjectCategoryDTO> convertBoListToCategoryDtoLIst(List<SubjectCategoryBO> subjectCategoryBOList);

}
