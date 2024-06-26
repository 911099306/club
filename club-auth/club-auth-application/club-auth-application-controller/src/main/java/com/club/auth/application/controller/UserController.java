package com.club.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.club.auth.application.converter.AuthUserDTOConverter;
import com.club.auth.application.dto.AuthUserDTO;
import com.club.auth.domain.entity.AuthUserBO;
import com.club.auth.domain.service.AuthUserDomainService;
import com.club.subject.common.entity.Result;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/18
 **/
@Slf4j
@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {

    private final AuthUserDomainService authUserDomainService;
    /**
     * 用户注册
     * @param authUserDTO 用户注册实体
     * @return
     */
    @PostMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);

            return Result.ok( authUserDomainService.register(authUserBO));
        } catch (Exception e) {
            log.error("UserController.register.err:{}", e.getMessage(), e);
            return Result.fail("注册失败！");
        }
    }


    /**
     * 用户更新
     * @param authUserDTO 用户注册实体
     * @return
     */
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            // checkUserInfo(authUserDTO);
            Preconditions.checkNotNull((authUserDTO.getId()),"用户id不可为空！");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);

            return Result.ok( authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.register.err:{}", e.getMessage(), e);
            return Result.fail("更新失败！");
        }
    }

    /**
     * 删除用户
     * @param authUserDTO 用户注册实体
     * @return
     */
    @PostMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            // checkUserInfo(authUserDTO);
            Preconditions.checkNotNull((authUserDTO.getId()),"用户id不可为空！");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);

            return Result.ok( authUserDomainService.delete(authUserBO));
        } catch (Exception e) {
            log.error("UserController.register.err:{}", e.getMessage(), e);
            return Result.fail("更新失败！");
        }
    }

    /**
     * 启用/禁用用户
     * @param authUserDTO 用户注册实体
     * @return
     */
    @PostMapping("changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            // checkUserInfo(authUserDTO);
            Preconditions.checkNotNull((authUserDTO.getId()),"用户id不可为空！");
            Preconditions.checkNotNull((authUserDTO.getStatus()),"用户状态不可为空！");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);

            return Result.ok( authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.register.err:{}", e.getMessage(), e);
            return Result.fail("更新失败！");
        }
    }

    /**
     * 启用/禁用用户
     * @param authUserDTO 用户注册实体
     * @return
     */
    @PostMapping("getUserInfo")
    public Result<Boolean> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            // checkUserInfo(authUserDTO);
            Preconditions.checkNotNull((authUserDTO.getId()),"用户id不可为空！");
            Preconditions.checkNotNull((authUserDTO.getStatus()),"用户状态不可为空！");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);

            return Result.ok( authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.register.err:{}", e.getMessage(), e);
            return Result.fail("更新失败！");
        }
    }

    private void checkUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()),"用户名不可为空！");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()),"密码不可为空！");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()),"邮件地址不可为空！");
    }


    // 测试登录，浏览器访问： http://localhost:3011/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public SaResult  doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            System.out.println("-----------------------------------------");
            System.out.println("执行登录");
            System.out.println("-----------------------------------------");
            StpUtil.login("monou");
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            // 第3步，返回给前端
            return SaResult.data(tokenInfo);
        }
        return SaResult.error();
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        boolean login = StpUtil.isLogin();

        return login ? SaResult.data("登录成功") : SaResult.error("登录失败");
    }

}

