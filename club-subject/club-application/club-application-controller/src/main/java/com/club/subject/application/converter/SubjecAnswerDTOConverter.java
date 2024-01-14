package com.club.subject.application.converter;

import com.club.subject.application.dto.SubjectAnswerDTO;
import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.application.dto.SubjectInfoDTO;
import com.club.subject.domain.entity.SubjectAnswerBO;
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
public interface SubjecAnswerDTOConverter {

    SubjecAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjecAnswerDTOConverter.class);

    SubjectAnswerBO convertDtoToAnswerBo(SubjectAnswerDTO subjectAnswerDTO);
    List<SubjectAnswerBO> convertListDtoToAnswerBo(List<SubjectAnswerDTO> subjectAnswerDTOList);
    List<SubjectAnswerDTO> convertBoListToAnswerDtoLIst(List<SubjectAnswerBO> subjectAnswerBOList);

}
