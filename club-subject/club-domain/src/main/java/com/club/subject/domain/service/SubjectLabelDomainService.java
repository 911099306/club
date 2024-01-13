package com.club.subject.domain.service;

import com.club.subject.domain.entity.SubjectCategoryBO;
import com.club.subject.domain.entity.SubjectLabelBO;
import com.club.subject.infra.basic.entity.SubjectLabel;

import java.util.List;

/**
 * 标签服务相关
 * 1
 *
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
public interface SubjectLabelDomainService {

    /**
     * 新增标签
     *
     * @param subjectCategoryBO 实例对象
     */
    Boolean add(SubjectLabelBO subjectCategoryBO);

    /**
     * 更新标签信息
     *
     * @param subjectLabelBO
     * @return
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签信息
     * @param subjectLabelBo
     * @return
     */
    Boolean delete(SubjectLabelBO subjectLabelBo);

    /**
     * 根据分类id 查询标签信息
     * @param subjectLabelBO
     * @return
     */
    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
