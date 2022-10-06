package com.wds.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-01 16:54
 *
 * 收货地址 实体类
 */
@Data
public class AddressBook implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private String consignee;
    private Integer sex;
    private String phone;

    private String provinceCode;
    private String provinceName;

    private String cityCode;
    private String cityName;

    private String districtCode;
    private String districtName;

    private String detail;
    private String label;

    private Integer isDefault;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUser;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUser;


//    private Integer isDeleted;
}
