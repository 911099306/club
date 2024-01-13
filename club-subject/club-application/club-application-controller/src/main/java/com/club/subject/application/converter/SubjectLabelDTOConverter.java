package com.club.subject.application.converter;

import com.club.subject.application.dto.SubjectCategoryDTO;
import com.club.subject.application.dto.SubjectLabelDTO;
import com.club.subject.domain.entity.SubjectCategoryBO;
import com.club.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 标签 DTO 转换信息
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
@Mapper
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDtoToLabelBo(SubjectLabelDTO subjectLabelDTO);
    List<SubjectLabelDTO> convertBoListToLabelDtoLIst(List<SubjectLabelBO> subjectLabelBOList);

}
