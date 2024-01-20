package com.club.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.club.auth.application.converter.AuthPermissionDTOConverter;
import com.club.auth.application.converter.AuthRoleDTOConverter;
import com.club.auth.application.dto.AuthPermissionDTO;
import com.club.auth.application.dto.AuthRoleDTO;
import com.club.auth.domain.entity.AuthPermissionBO;
import com.club.auth.domain.entity.AuthRoleBO;
import com.club.auth.domain.service.AuthPermissionDomainService;
import com.club.auth.domain.service.AuthRoleDomainService;
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
@RequestMapping("/permission/")
@RequiredArgsConstructor
public class PermissionController {

    private final AuthPermissionDomainService authPermissionDomainService;
    /**
     * 新增权限
     * @param authPermissionDTO 用户注册实体
     * @return
     */
    @PostMapping("add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.add.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()),"权限名称不可为空！");
            Preconditions.checkNotNull((authPermissionDTO.getParentId()),"权限父id不能为空！");

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);

            return Result.ok( authPermissionDomainService.add(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.add.err:{}", e.getMessage(), e);
            return Result.fail("增加权限失败！");
        }
    }


    /**
     * 权限更新
     * @param authPermissionDTO 权限更新实体
     * @return
     */
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.update.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull((authPermissionDTO.getId()),"权限id不可为空！");

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);


            return Result.ok( authPermissionDomainService.update(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.register.err:{}", e.getMessage(), e);
            return Result.fail("权限更新失败！");
        }
    }

    /**
     * 删除权限
     * @param authPermissionDTO 删除权限
     * @return
     */
    @PostMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authPermissionDTO));
            }

            Preconditions.checkNotNull((authPermissionDTO.getId()),"权限id不可为空！");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);


            return Result.ok( authPermissionDomainService.delete(authPermissionBO));
        } catch (Exception e) {
            log.error("UserController.register.err:{}", e.getMessage(), e);
            return Result.fail("删除权限失败！");
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

