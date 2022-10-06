package com.wds.service.impl;

import cn.hutool.core.util.IdUtil;
import com.wds.common.BaseContext;
import com.wds.dto.SetmealDto;
import com.wds.entity.SetmealDish;
import com.wds.mapper.SetMealDishMapper;
import com.wds.service.SetMealDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-28 15:10
 */
@Service
public class SetMealDishServiceImpl implements SetMealDishService {
    @Autowired
    private SetMealDishMapper mapper;

    @Override
    public void updateSetMealDish(SetmealDto dto) {
     //删除原有的
        mapper.deleteBySetmealId(dto.getId());
     //加入新的
        List<SetmealDish> dishes = dto.getSetmealDishes();
        Date now = new Date();
        Long user = BaseContext.getEmpId();
        for (SetmealDish dish : dishes) {

            dish.setId(IdUtil.getSnowflakeNextId());
            dish.setSetmealId(dto.getId());

            dish.setCreateTime(now);
            dish.setUpdateTime(now);
            dish.setCreateUser(user);
            dish.setUpdateUser(user);
        }
        mapper.addNewSetmealDish(dto.getSetmealDishes());
    }
}
