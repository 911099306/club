package com.club.auth.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用戶信息dto
 *
 * @author serendipity
 * @version 1.0
 * @date 2024/1/18
 */
@Data
public class AuthUserDTO implements Serializable {

    private Long id;

    private String userName;

    private String nickName;

    private String email;

    private String phone;

    private String password;

    private Integer sex;

    private String avatar;

    private Integer status;

    private String introduce;

    private String extJson;

}

