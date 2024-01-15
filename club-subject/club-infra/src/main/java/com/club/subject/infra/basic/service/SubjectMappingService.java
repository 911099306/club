package com.club.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.club.subject.infra.basic.entity.SubjectMapping;

import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务接口
 *
 * @author makejava
 * @since 2024-01-13 17:50:08
 */
public interface SubjectMappingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMapping queryById(Long id);



    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    SubjectMapping insert(SubjectMapping subjectMapping);

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    int update(SubjectMapping subjectMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询分类下标签的ids
     * @param subjectMapping
     * @return
     */
    List<SubjectMapping> queryLabelIds(SubjectMapping subjectMapping);

    /**
     * 批量插入mapping 数据
     * @param subjectMappingList
     */
    void batchInsert(List<SubjectMapping> subjectMappingList);

    List<SubjectMapping> queryLabelId(SubjectMapping subjectMapping);
}
