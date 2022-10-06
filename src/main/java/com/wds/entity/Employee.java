package com.wds.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.apache.ibatis.annotations.Property;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-21 7:11
 * 员工实体
 */
@Data
public class Employee implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    private String username;

    private String password;

    private String phone;

    private Integer sex;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long idNumber;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUser;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUser;
}
