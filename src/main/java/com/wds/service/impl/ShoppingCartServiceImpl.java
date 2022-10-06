package com.wds.service.impl;

import cn.hutool.core.util.IdUtil;
import com.wds.common.BaseContext;
import com.wds.entity.ShoppingCart;
import com.wds.exception.CustomException;
import com.wds.mapper.ShoppingCartMapper;
import com.wds.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-30 14:50
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper mapper;

    @Override
    public List<ShoppingCart> getListByUserId(Long userId) {
        List<ShoppingCart> list = mapper.getListByUserId(userId);
        return list;
    }

    @Override
    public void addItem(ShoppingCart item) {
        item.setId(IdUtil.getSnowflakeNextId());
        item.setCreateTime(new Date());
        item.setUserId(BaseContext.getUserId());
        item.setNumber(1);
        mapper.save(item);
    }

    @Override
    public ShoppingCart getByDishId(Long dishId, Long setmealId) {
        ShoppingCart cart = mapper.getByDishId(dishId, setmealId, BaseContext.getUserId());
        return cart;
    }

    @Override
    public void updateNumberById(Long id) {
        mapper.addNumberById(id);
    }

    @Override
    public void sub(Long dishId, Long setmealId) {
        if (dishId == null || setmealId == null) {
            mapper.sub(dishId, setmealId);
        }else {
            throw new CustomException("传值异常: setmealId" +setmealId + "dishId :"+dishId);
        }
    }

    @Override
    public void cleanCurrentUserCart() {
        mapper.clean(BaseContext.getUserId());
    }
}
