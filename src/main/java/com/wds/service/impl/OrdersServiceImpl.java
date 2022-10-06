package com.wds.service.impl;

import cn.hutool.db.sql.Order;
import com.wds.common.BaseContext;
import com.wds.dto.Page;
import com.wds.entity.Orders;
import com.wds.mapper.OrdersMapper;
import com.wds.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-06 8:55
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper mapper;

    @Override
    public Page getUserPage(int page, int pageSize) {
        Page result = new Page();
        List<Orders> list = mapper.getUserPage((page - 1) * pageSize, pageSize, BaseContext.getUserId());
        result.setRecords(new ArrayList<>(list));
        return result;
    }

    @Override
    public void saveOrder(Orders order) {
        mapper.save(order);
    }

    @Override
    public Page getPage(int page, int pageSize, Long number, Date begin, Date end) {
        Page result = new Page();
        int count = mapper.getCount();
        String strNumber = null;
        if (number != null) {
            strNumber = "%" + number + "%";
        }

        List<Order> list = mapper.getPage((page - 1) * pageSize, pageSize, strNumber , begin, end);

        result.setSize(pageSize);
        result.setCurrent(page);

        result.setTotal(count);
        result.setRecords(new ArrayList<>(list));
        result.setPages(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
        return result;
    }

    @Override
    public void updateStatus(Orders orders) {
        mapper.updateStatusById(orders);
    }
}
