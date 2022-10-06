package com.wds.mapper;

import com.wds.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-06 11:26
 */
@Mapper
public interface OrderDetailMapper {
    void save(List<OrderDetail> details);

}
