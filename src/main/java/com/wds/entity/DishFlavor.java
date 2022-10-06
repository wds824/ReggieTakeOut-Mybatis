package com.wds.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-26 16:50
 * 菜品口味
 */
@Data
public class DishFlavor implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dishId;

    private String name;
    private String value;

    private Date createTime;
    private Date updateTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUser;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUser;

//    private Integer isDeleted;
}
