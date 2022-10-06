package com.wds.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-30 14:39
 *
 * 购物车实体
 */
@Data
public class ShoppingCart implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId; //user_id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dishId; //dish_id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long setmealId;//setmeal_id

    private String name;
    private String image;

    private String dishFlavor;//dish_flavor

    private Integer number;
    private BigDecimal amount;

    private Date createTime;//create_time

}

