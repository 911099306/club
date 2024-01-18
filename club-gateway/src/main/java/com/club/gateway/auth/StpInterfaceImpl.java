package com.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.club.gateway.redis.RedisUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 *
 * @author serendipity
 * @version 1.0
 * @date 2024/1/16
 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final RedisUtil redisUtil;
    private final String authPermissionPrefix = "auth.permission";
    private final String authRolePrefix = "auth.role";

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        // 1. 直接与数据库交互
        // 2. redis 缓存 ** (保证强一致性） --> 选用
        // 3. redis 缓存 + 调用微服务

        return getAuth(loginId.toString(), authPermissionPrefix);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        return getAuth(loginId.toString(), authRolePrefix);
    }

    public List<String> getAuth(String loginId, String prefix) {
        String authKey = redisUtil.buildKey(prefix, loginId);
        String authValue = redisUtil.get(authKey);
        if (StringUtils.isBlank(authValue)) {
            return Collections.emptyList();
        }
        return new Gson().fromJson(authValue, List.class);

    }

}
