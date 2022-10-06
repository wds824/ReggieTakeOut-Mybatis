package com.wds.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-21 19:01
 * 分页对象
 */
@Data
public class Page implements Serializable {
    // 记录
    private List<Object> records;

    // 记录总数
    private Integer total;

    // 页数
    private Integer pages;

    // 当前页
    private Integer current;

    // 页面大小
    private Integer size;
}
