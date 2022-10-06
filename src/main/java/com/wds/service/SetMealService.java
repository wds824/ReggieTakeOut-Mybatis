package com.wds.service;

import com.wds.dto.Page;
import com.wds.dto.SetmealDto;
import com.wds.entity.Dish;
import com.wds.entity.Setmeal;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-28 15:11
 */
public interface SetMealService {
    Page getPage(int currentPage, int pageSize, String name);

    SetmealDto getById(Long id);

    void update(SetmealDto dto);

    List<Setmeal> getListByCategoryId(Long categoryId);

    List<Dish> getDishById(Long id);
}
