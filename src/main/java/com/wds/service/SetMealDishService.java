package com.wds.service;

import com.wds.dto.SetmealDto;
import com.wds.entity.Dish;
import com.wds.entity.SetmealDish;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-28 15:10
 */
public interface SetMealDishService {
    void updateSetMealDish(SetmealDto dto);

    List<SetmealDish> getDishList(Long id);

    List<Dish> getDishAndImage(Long id);
}
