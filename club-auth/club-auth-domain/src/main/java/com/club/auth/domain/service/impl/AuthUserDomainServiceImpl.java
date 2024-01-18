package com.club.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.club.auth.common.enums.AuthUserStatusEnum;
import com.club.auth.common.enums.IsDeletedFlagEnum;
import com.club.auth.domain.convert.AuthUserBOConverter;
import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.domain.service.AuthUserDomainService;
import com.club.auth.infra.basic.entity.AuthUser;
import com.club.auth.infra.basic.service.AuthUserService;
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
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    private final AuthUserService authUserService;

    /**
     * 用户注册接口
     * @param authUserBO
     */
    @Override
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), authUserBO.getUserName() ));
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        // TODO 建立初步角色的关联
        // 将当前用户的角色和权限都刷新的 redis
        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.update(authUser);
        // TODO 任何更新，都要同步到 redis 内
        return count > 0;

    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {

        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        // TODO 任何更新，都要同步到 redis 内
        return count > 0;
    }
}
