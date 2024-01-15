package com.club.subject.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求信息
 *
 * @author serendipity
 * @version 1.0
 * @date 2024/1/14
 **/
public class PageInfo implements Serializable {

    private Integer pageNo = 1;

    private Integer pageSize = 20;

    public Integer getPageNo() {
        if (pageNo == null || pageNo < 1) {
            return 1;
        }
        return pageNo;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1 || pageSize > Integer.MAX_VALUE) {
            return 20;
        }
        return pageSize;
    }
}
