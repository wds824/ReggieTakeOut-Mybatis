package com.wds.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-23 16:38
 * 分类实体 （type = 1 菜品  = 2 套餐）
 */
@Data
public class Category implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    // 注释
    private Integer type;
    private String name;
    private Integer sort;

    private Date createTime;

    private Date updateTime;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUser;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUser;
}
