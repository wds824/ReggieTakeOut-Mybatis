package com.wds.service;

import com.wds.entity.OrderDetail;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-06 10:59
 */
public interface OrderDetailService {
    void save(List<OrderDetail> details);

}
