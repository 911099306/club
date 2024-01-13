package com.club.subject.common.enums;

import lombok.Getter;

/**
 * 删除状态
 *
 * @author serendipity
 * @version 1.0
 * @date 2024/1/12
 **/
@Getter
public enum IsDeletedFlagEnum {
    DELETED(1,"已删除"),
    UN_DELETED(0,"未删除");

    public int code;

    public String desc;

    IsDeletedFlagEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static IsDeletedFlagEnum getByCode(int codeVal){
        for(IsDeletedFlagEnum resultCodeEnum : IsDeletedFlagEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }
}
