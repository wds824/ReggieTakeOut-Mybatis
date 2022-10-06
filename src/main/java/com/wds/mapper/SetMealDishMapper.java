package com.wds.mapper;

import com.wds.entity.Dish;
import com.wds.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-28 15:08
 */
@Mapper
public interface SetMealDishMapper {
    List<SetmealDish> getDishList(Long id);

    void deleteBySetmealId(Long id);

    void addNewSetmealDish(List<SetmealDish> lsit);

    List<Dish> getDishAndImage(Long setmealId);
}
