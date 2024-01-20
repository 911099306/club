package com.club.auth.domain.service.impl;

import com.club.auth.common.enums.IsDeletedFlagEnum;
import com.club.auth.domain.convert.AuthPermissionBOConverter;
import com.club.auth.domain.entity.AuthRolePermissionBO;
import com.club.auth.domain.service.AuthRolePermissionDomainService;
import com.club.auth.infra.basic.entity.AuthRolePermission;
import com.club.auth.infra.basic.service.AuthRolePermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/18
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    private final AuthRolePermissionService authRolePermissionService;
    /**
     * 角色 权限关联
     * @param authRolePermissionBO
     * @return
     */
    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        List<AuthRolePermission> rolePermissionList = new LinkedList<>();
        Long roleId = authRolePermissionBO.getRoleId();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
            rolePermissionList.add(authRolePermission);
        });
        int count = authRolePermissionService.batchInsert(rolePermissionList);
        return count > 0;
    }
}
