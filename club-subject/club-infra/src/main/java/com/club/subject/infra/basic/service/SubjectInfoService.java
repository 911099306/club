package com.club.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.club.subject.infra.basic.entity.SubjectInfo;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author makejava
 * @since 2024-01-13 21:39:57
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);



    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据条件查询符合条件的记录数量
     * @param subjectInfo
     * @param categoryId
     * @param labelId
     * @return
     */
    int countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId);

    /**
     * 分页查询
     * @param subjectInfo
     * @param categoryId
     * @param labelId
     * @param start
     * @param pageSize
     * @return
     */
    List<SubjectInfo> queryPage(SubjectInfo subjectInfo, Long categoryId, Long labelId, int start, Integer pageSize);
}
