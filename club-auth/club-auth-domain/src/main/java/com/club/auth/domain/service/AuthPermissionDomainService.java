package com.club.auth.domain.service;

import com.club.auth.domain.entity.AuthPermissionBO;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/18
 **/
public interface AuthPermissionDomainService {
    /**
     * 增加权限
     * @param authPermissionBO
     * @return
     */
    Boolean add(AuthPermissionBO authPermissionBO);

    /**
     * 更改权限
     * @param authPermissionBO
     * @return
     */
    Boolean update(AuthPermissionBO authPermissionBO);

    /**
     * 删除权限
     * @param authPermissionBO
     * @return
     */
    Boolean delete(AuthPermissionBO authPermissionBO);
}
