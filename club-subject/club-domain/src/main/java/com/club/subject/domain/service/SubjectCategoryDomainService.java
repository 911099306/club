package com.club.subject.domain.service;

import com.club.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
public interface SubjectCategoryDomainService {

    /**
     * 新增数据
     *
     * @param subjectCategoryBO 实例对象
     */
    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询 岗位 大类
     * @return
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 更新分类信息
     * @param subjectCategoryBO
     * @return
     */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    /**
     * 删除分类信息
     * @param subjectCategoryBO
     * @return
     */
    Boolean delete(SubjectCategoryBO subjectCategoryBO);
}
