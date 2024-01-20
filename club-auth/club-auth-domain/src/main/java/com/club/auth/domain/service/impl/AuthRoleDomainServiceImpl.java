package com.club.auth.domain.service.impl;

import com.club.auth.common.enums.IsDeletedFlagEnum;
import com.club.auth.domain.convert.AuthRoleBOConverter;
import com.club.auth.domain.entity.AuthRoleBO;
import com.club.auth.domain.service.AuthRoleDomainService;
import com.club.auth.infra.basic.entity.AuthRole;
import com.club.auth.infra.basic.service.AuthRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/18
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    private final AuthRoleService authRoleService;

    /**
     * 新增角色
     *
     * @param authRoleBO
     * @return
     */
    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        int count = authRoleService.insert(authRole);
        return count > 0;
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {

        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOToEntity(authRoleBO);
        int count = authRoleService.update(authRole);
        return count > 0;
    }

    /**
     * 删除角色
     *
     * @param authRoleBO
     * @return
     */
    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {

        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.code);
        int count = authRoleService.update(authRole);
        return count > 0;
    }

}
