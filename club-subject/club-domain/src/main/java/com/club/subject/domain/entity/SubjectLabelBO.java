package com.club.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签表(SubjectLabel)
 *
 * @author makejava
 * @since 2024-01-13 17:00:51
 */
@Data
public class SubjectLabelBO implements Serializable {
    private static final long serialVersionUID = 269531785425685625L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 分类id
     */
    private Long categoryId;


}

