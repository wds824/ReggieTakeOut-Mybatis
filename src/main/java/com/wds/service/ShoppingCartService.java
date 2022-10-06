package com.wds.service;

import com.wds.entity.ShoppingCart;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-30 14:50
 */
public interface ShoppingCartService {
    List<ShoppingCart> getListByUserId(Long userId);

    void addItem(ShoppingCart item);

    ShoppingCart getByDishId(Long dishId, Long setmealId);

    void updateNumberById(Long id);

    void sub(Long dishId, Long setmealId);

    void cleanCurrentUserCart();

}
