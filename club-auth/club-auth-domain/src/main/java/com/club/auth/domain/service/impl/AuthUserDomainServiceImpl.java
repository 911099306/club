package com.club.auth.domain.service.impl;

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
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        return count > 0;
    }
}
