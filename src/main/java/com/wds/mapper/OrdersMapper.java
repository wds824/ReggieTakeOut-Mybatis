package com.wds.mapper;

import cn.hutool.db.sql.Order;
import com.wds.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-06 8:55
 */
@Mapper
public interface OrdersMapper {
    List<Orders> getUserPage(int page, int pageSize, Long userId);

    void save(Orders orders);

    int getCount(Long number);

    List<Order> getPage(int page, int pageSize, Long number, Date begin, Date end);

    void updateStatusById(Orders orders);
}
