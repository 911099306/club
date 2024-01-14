package com.club.subject.application.converter;

import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.application.dto.SubjectInfoDTO;
import com.club.subject.domain.entity.SubjectCategoryBO;
import com.club.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
@Mapper
public interface SubjectInfoDTOConverter {

    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDtoToInfoBo(SubjectInfoDTO subjectInfoDTO);
    List<SubjectCategoryDTO> convertBoListToInfoDtoLIst(List<SubjectInfoBO> subjectInfoBOList);

}
