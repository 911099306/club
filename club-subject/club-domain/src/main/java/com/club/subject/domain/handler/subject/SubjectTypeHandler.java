package com.club.subject.domain.handler.subject;

import com.club.subject.common.enums.SubjectInfoTypeEnum;
import com.club.subject.domain.entity.SubjectInfoBO;
import com.club.subject.infra.basic.entity.SubjectInfo;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/13
 **/
public interface SubjectTypeHandler {

    /**
     * 枚举身份的识别
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 实际的题目输入
     */
    void add(SubjectInfoBO subjectInfoBO );

}
