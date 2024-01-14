package com.club.subject.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/13
 **/
@Data
@Builder
public class SubjectAnswerBO implements Serializable {
    /**
     * 答案选项标识
     */
    private Integer optionType;

    /**
     * 答案
     */
    private String optionContent;

    /**
     * 是否正确
     */
    private Integer isCorrect;
}
