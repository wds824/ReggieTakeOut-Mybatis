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
 * @since 2022-09-25 16:21
 * <p>
 * 菜品
 */
@Data
public class Dish implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    private BigDecimal price;

    private String code;

    private String image;

    private String description;

    private Integer status;

    private Integer sort;

    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUser;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUser;


//    无用的字段
//    private Integer is_deleted;
}
