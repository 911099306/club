package com.club.auth.domain.service;

import com.club.auth.domain.entity.AuthRolePermissionBO;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/18
 **/
public interface AuthRolePermissionDomainService {
    /**
     * 用户角色权限关联
     * @param authRolePermissionBO
     * @return
     */
    Boolean add(AuthRolePermissionBO authRolePermissionBO);
}
