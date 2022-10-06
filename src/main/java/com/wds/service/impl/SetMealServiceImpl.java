package com.wds.service.impl;

import com.wds.common.BaseContext;
import com.wds.dto.Page;
import com.wds.dto.SetmealDto;
import com.wds.entity.Dish;
import com.wds.entity.Setmeal;
import com.wds.entity.SetmealDish;
import com.wds.exception.CustomException;
import com.wds.mapper.SetMealDishMapper;
import com.wds.mapper.SetMealMapper;
import com.wds.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-28 15:11
 */
@Service
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealMapper mapper;

    @Autowired
    private SetMealDishMapper setMealDishMapper;

    @Override
    public Page getPage(int currentPage, int pageSize, String name) {
        if (name != null) {
            name = "%" + name + "%";
        }

        int count = mapper.count();
        List<SetmealDto> list = mapper.getPage((currentPage - 1) * pageSize, pageSize, name);

        Page page = new Page();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        page.setTotal(count);
        page.setRecords(new ArrayList<>(list));
        page.setPages((double) count / pageSize == 0 ? count / pageSize : count / pageSize + 1);

        return page;
    }

    @Override
    public SetmealDto getById(Long id) {
        SetmealDto dto = mapper.getById(id);
        List<SetmealDish> list = setMealDishMapper.getDishList(id);
        dto.setSetmealDishes(new ArrayList<>(list));
        return dto;
    }

    @Override
    public void update(SetmealDto dto) {
        dto.setUpdateTime(new Date());
        dto.setUpdateUser(BaseContext.getEmpId());
        if (BaseContext.getEmpId() == null) {
            throw new CustomException("登录状态异常，请尝试重新登录！");
        }
        mapper.updateById(dto);
    }

    @Override
    public List<Setmeal> getListByCategoryId(Long categoryId) {
        List<Setmeal> result = mapper.getList(categoryId);
        return result;
    }

    @Override
    public List<Dish> getDishById(Long id) {
        List<Dish> list = setMealDishMapper.getDishAndImage(id);
        return list;

    }
}
