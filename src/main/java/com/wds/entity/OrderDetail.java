package com.wds.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细
 */
@Data
public class OrderDetail implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    //名称
    private String name;

    //订单id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;


    //菜品id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dishId;


    //套餐id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long setmealId;


    //口味
    private String dishFlavor;


    //数量
    private Integer number;

    //金额
    private BigDecimal amount;

    //图片
    private String image;
}
