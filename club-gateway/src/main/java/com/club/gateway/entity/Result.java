package com.club.gateway.entity;

import com.club.gateway.enums.ResultCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/16
 **/
@Data
@Builder
public class Result<T> implements Serializable {
    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    public static Result ok() {

        return Result.builder()
                .success(true)
                .code(ResultCodeEnum.SUCCESS.code)
                .message(ResultCodeEnum.SUCCESS.desc)
                .build();
    }

    public static <T> Result ok(T data) {
        return Result.builder()
                .success(true)
                .code(ResultCodeEnum.SUCCESS.code)
                .message(ResultCodeEnum.SUCCESS.desc)
                .data(data)
                .build();
    }

    public static Result fail() {

        return Result.builder()
                .success(false)
                .code(ResultCodeEnum.FAIL.code)
                .message(ResultCodeEnum.FAIL.desc)
                .build();
    }

    public static <T> Result fail(T data) {
        return Result.builder()
                .success(false)
                .code(ResultCodeEnum.FAIL.code)
                .message(ResultCodeEnum.FAIL.desc)
                .data(data)
                .build();
    }

    public static <T> Result fail(Integer code, String message) {
        return Result.builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }

}
