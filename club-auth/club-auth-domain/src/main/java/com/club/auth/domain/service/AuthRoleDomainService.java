package com.club.auth.domain.service;

import com.club.auth.domain.entity.AuthRoleBO;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/18
 **/
public interface AuthRoleDomainService {

    /**
     * 新增角色
     * @param authRoleBO
     * @return
     */
    Boolean add(AuthRoleBO authRoleBO);

    /**
     * 更新用户
     * @param authRoleBO
     * @return
     */
    Boolean update(AuthRoleBO authRoleBO);

    /**
     * 删除角色
     * @param authRoleBO
     * @return
     */
    Boolean delete(AuthRoleBO authRoleBO);
}
