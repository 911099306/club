package com.club.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.club.auth.application.converter.AuthRoleDTOConverter;
import com.club.auth.application.converter.AuthUserDTOConverter;
import com.club.auth.application.dto.AuthRoleDTO;
import com.club.auth.application.dto.AuthUserDTO;
import com.club.auth.domain.entity.AuthRoleBO;
import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.domain.service.AuthRoleDomainService;
import com.club.auth.domain.service.AuthUserDomainService;
import com.club.auth.infra.basic.entity.AuthRole;
import com.club.subject.common.entity.Result;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/18
 **/
@Slf4j
@RestController
@RequestMapping("/role/")
@RequiredArgsConstructor
public class RoleController {

    private final AuthRoleDomainService authRoleDomainService;
    /**
     * 新增角色
     * @param authRoleDTO 用户注册实体
     * @return
     */
    @PostMapping("add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.add.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()),"角色Key不可为空！");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()),"角色名称不可为空！");

            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);

            return Result.ok( authRoleDomainService.add(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.add.err:{}", e.getMessage(), e);
            return Result.fail("增加角色失败！");
        }
    }


    /**
     * 用户更新
     * @param authRoleDTO 用户注册实体
     * @return
     */
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.update.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull((authRoleDTO.getId()),"角色id不可为空！");

            AuthRoleBO authRoleBO  = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);

            return Result.ok( authRoleDomainService.update(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.register.err:{}", e.getMessage(), e);
            return Result.fail("角色更新失败！");
        }
    }

    /**
     * 删除用户
     * @param authRoleDTO 用户注册实体
     * @return
     */
    @PostMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authRoleDTO));
            }

            Preconditions.checkNotNull((authRoleDTO.getId()),"用户id不可为空！");
            AuthRoleBO authRoleBO  = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);

            return Result.ok( authRoleDomainService.delete(authRoleBO));
        } catch (Exception e) {
            log.error("UserController.register.err:{}", e.getMessage(), e);
            return Result.fail("更新失败！");
        }
    }

    // /**
    //  * 启用/禁用用户
    //  * @param authUserDTO 用户注册实体
    //  * @return
    //  */
    // @PostMapping("changeStatus")
    // public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
    //     try {
    //         if (log.isInfoEnabled()) {
    //             log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
    //         }
    //         // checkUserInfo(authUserDTO);
    //         Preconditions.checkNotNull((authUserDTO.getId()),"用户id不可为空！");
    //         Preconditions.checkNotNull((authUserDTO.getStatus()),"用户状态不可为空！");
    //         AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
    //
    //         return Result.ok( authUserDomainService.update(authUserBO));
    //     } catch (Exception e) {
    //         log.error("UserController.register.err:{}", e.getMessage(), e);
    //         return Result.fail("更新失败！");
    //     }
    // }
    //
    // /**
    //  * 启用/禁用用户
    //  * @param authUserDTO 用户注册实体
    //  * @return
    //  */
    // @PostMapping("getUserInfo")
    // public Result<Boolean> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
    //     try {
    //         if (log.isInfoEnabled()) {
    //             log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
    //         }
    //         // checkUserInfo(authUserDTO);
    //         Preconditions.checkNotNull((authUserDTO.getId()),"用户id不可为空！");
    //         Preconditions.checkNotNull((authUserDTO.getStatus()),"用户状态不可为空！");
    //         AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
    //
    //         return Result.ok( authUserDomainService.update(authUserBO));
    //     } catch (Exception e) {
    //         log.error("UserController.register.err:{}", e.getMessage(), e);
    //         return Result.fail("更新失败！");
    //     }
    // }
    //
    //
    //
    // // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    // @RequestMapping("doLogin")
    // public SaResult  doLogin(String username, String password) {
    //     // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
    //     if("zhang".equals(username) && "123456".equals(password)) {
    //         System.out.println("-----------------------------------------");
    //         System.out.println("执行登录");
    //         System.out.println("-----------------------------------------");
    //         StpUtil.login(10001);
    //         SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
    //         // 第3步，返回给前端
    //         return SaResult.data(tokenInfo);
    //     }
    //     return SaResult.error();
    // }
    //
    // // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    // @RequestMapping("isLogin")
    // public SaResult isLogin() {
    //     boolean login = StpUtil.isLogin();
    //
    //     return login ? SaResult.data("登录成功") : SaResult.error("登录失败");
    // }

}

