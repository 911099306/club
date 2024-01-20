package com.club.auth.domain.service.impl;

import com.club.auth.common.enums.IsDeletedFlagEnum;
import com.club.auth.domain.convert.AuthPermissionBOConverter;
import com.club.auth.domain.entity.AuthPermissionBO;
import com.club.auth.domain.service.AuthPermissionDomainService;
import com.club.auth.infra.basic.entity.AuthPermission;
import com.club.auth.infra.basic.service.AuthPermissionService;
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
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    private final AuthPermissionService authPermissionService;
    /**
     * 增加权限
     * @param authPermissionBO
     * @return
     */
    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        int count = authPermissionService.insert(authPermission);
        return count > 0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }

    /**
     * 删除权限
     * @param authPermissionBO
     * @return
     */
    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {

        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.code);
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }

}
