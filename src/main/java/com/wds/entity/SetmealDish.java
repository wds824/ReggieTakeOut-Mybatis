package com.wds.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-28 14:59
 * 套餐菜品实体
 */
@Data
public class SetmealDish implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long setmealId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long dishId;

    private String name;
    private BigDecimal price;
    private Integer copies;
    private Integer sort;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUser;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUser;
}
