package com.club.auth.domain.convert;

import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户bo转换器
 *
 * @author serendipity
 * @version 1.0
 * @date 2024/1/18
 */
@Mapper
public interface AuthUserBOConverter {

    AuthUserBOConverter INSTANCE = Mappers.getMapper(AuthUserBOConverter.class);

    AuthUser convertBOToEntity(AuthUserBO authUserBO);

    AuthUserBO convertEntityToBO(AuthUser authUser);

}
