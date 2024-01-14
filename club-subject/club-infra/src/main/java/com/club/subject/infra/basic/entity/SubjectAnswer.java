package com.club.subject.infra.basic.entity;

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
public class SubjectAnswer implements Serializable {
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
