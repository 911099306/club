package com.club.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.club.auth.common.enums.AuthUserStatusEnum;
import com.club.auth.common.enums.IsDeletedFlagEnum;
import com.club.auth.domain.convert.AuthUserBOConverter;
import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.domain.redis.RedisConfig;
import com.club.auth.domain.redis.RedisUtil;
import com.club.auth.domain.service.AuthPermissionDomainService;
import com.club.auth.domain.service.AuthUserDomainService;
import com.club.auth.infra.basic.entity.*;
import com.club.auth.infra.basic.service.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.club.auth.domain.constants.AuthConstant.NORMAL_USER;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/18
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    private final RedisUtil redisUtil;
    private final AuthUserService authUserService;
    private final AuthRoleService authRoleService;
    private final AuthUserRoleService authUserRoleService;
    private final AuthPermissionService authPermissionService;
    private final AuthRolePermissionService authRolePermissionService;

    private final String authPermissionPrefix = "auth.permission";
    private final String authRolePrefix = "auth.role";


    /**
     * 用户注册接口
     * @param authUserBO
     */
    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), authUserBO.getUserName() ));
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        // 建立用户和角色的关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        Long roleId = roleResult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        authUserRoleService.insert(authUserRole);
        
        // 将当前用户的角色和权限都刷新的 redis
        String roleKey = redisUtil.buildKey(authRolePrefix,authUser.getUserName());
        List<AuthRole> roleList = new LinkedList<>();
        roleList.add(authRole);
        redisUtil.set(roleKey, new Gson().toJson(roleList));

        // 根据role 查询他的key，然后
        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(authRole.getId());
        List<AuthRolePermission> rolePermissionList = authRolePermissionService.queryByCondition(authRolePermission);
        List<Long> permissionIdList = rolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
        // 根据roleId查询权限
        List<AuthPermission> permissionList = authPermissionService.queryByRoleList(permissionIdList);
        String permissionKey = redisUtil.buildKey(authPermissionPrefix,authUser.getUserName());

        redisUtil.set(permissionKey, new Gson().toJson(permissionList));

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
