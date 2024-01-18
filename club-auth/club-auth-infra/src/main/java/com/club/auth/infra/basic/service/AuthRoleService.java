package com.club.auth.infra.basic.service;


import com.club.auth.infra.basic.entity.AuthRole;

import java.util.List;

/**
 * (AuthRole)表服务接口
 *
 * @author serendipity
 * @since 2024/01/18
 */
public interface AuthRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRole queryById(Long id);

    /**
     * 新增数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    int insert(AuthRole authRole);

    /**
     * 修改数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    int update(AuthRole authRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    
    /**
     * 根据条件查询角色
     */
    AuthRole queryByCondition(AuthRole authRole);

    List<AuthRole> queryByRoleList(List<Long> roleIdList);
    
}
