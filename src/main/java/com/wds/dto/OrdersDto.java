package com.wds.dto;

import com.wds.entity.OrderDetail;
import com.wds.entity.Orders;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private Integer sumNum;
    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
