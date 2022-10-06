package com.wds.service;

import com.wds.dto.Page;
import com.wds.entity.Orders;

import java.util.Date;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-06 8:55
 */
public interface OrdersService {
    Page getUserPage(int page, int pageSize);

    void saveOrder(Orders order);

    Page getPage(int page, int pageSize, Long number, Date begin, Date end);

    void updateStatus(Orders orders);
}
