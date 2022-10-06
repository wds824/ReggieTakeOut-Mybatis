package com.wds.service.impl;

import com.wds.entity.OrderDetail;
import com.wds.mapper.OrderDetailMapper;
import com.wds.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-06 10:59
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailMapper mapper;

    @Override
    public void save(List<OrderDetail> details) {
        mapper.save(details);
    }
}
