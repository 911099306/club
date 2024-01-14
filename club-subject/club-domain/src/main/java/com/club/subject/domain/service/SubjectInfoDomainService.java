package com.club.subject.domain.service;

import com.club.subject.common.entity.PageResult;
import com.club.subject.domain.entity.SubjectInfoBO;
import com.club.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * 标签服务相关
 * 1
 *
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     *
     * @param subjectInfoBO 实例对象
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 分页查询
     * @param subjectInfoBO
     * @return
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    // /**
    //  * 更新标签信息
    //  *
    //  * @param subjectLabelBO
    //  * @return
    //  */
    // Boolean update(SubjectLabelBO subjectLabelBO);
    //
    // /**
    //  * 删除标签信息
    //  * @param subjectLabelBo
    //  * @return
    //  */
    // Boolean delete(SubjectLabelBO subjectLabelBo);
    //
    // /**
    //  * 根据分类id 查询标签信息
    //  * @param subjectLabelBO
    //  * @return
    //  */
    // List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
