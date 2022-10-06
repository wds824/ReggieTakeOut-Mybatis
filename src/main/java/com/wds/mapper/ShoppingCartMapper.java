package com.wds.mapper;

import com.wds.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-30 14:45
 */
@Mapper
public interface ShoppingCartMapper {
    List<ShoppingCart> getListByUserId(Long userId);

    void save(ShoppingCart item);

    ShoppingCart getByDishId(Long dishId, Long setmealId, Long userId);

    void addNumberById(Long id);

    void sub(Long dishId, Long setmealId);

    void clean(Long userId);
}
