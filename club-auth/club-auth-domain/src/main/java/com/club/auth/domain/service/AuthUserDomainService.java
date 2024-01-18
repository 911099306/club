package com.club.auth.domain.service;


import com.club.auth.domain.entity.AuthUserBO;

import java.util.List;

/**
 * 用户领域Service实现类
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
public interface AuthUserDomainService {


    /**
     * 用户注册接口
     * @param authUserBO
     */
    Boolean register(AuthUserBO authUserBO);

    /**
     * 更新用户信息
     * @param authUserBO
     * @return
     */
    Boolean update(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);
}
