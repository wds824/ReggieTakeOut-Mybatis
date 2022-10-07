package com.wds.service.impl;

import com.wds.common.BaseContext;
import com.wds.common.Utils.CacheUtil;
import com.wds.dto.Page;
import com.wds.dto.SetmealDto;
import com.wds.entity.Dish;
import com.wds.entity.Setmeal;
import com.wds.entity.SetmealDish;
import com.wds.exception.CustomException;
import com.wds.mapper.SetMealDishMapper;
import com.wds.mapper.SetMealMapper;
import com.wds.service.SetMealDishService;
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
    private SetMealDishService setMealDishService;

    @Override
    public Page getPage(int currentPage, int pageSize, String name) {
        String cacheName = null;
        if (name == null) {
            cacheName = "setMeal_getPage_" + currentPage + "_" + pageSize;
            Object o = CacheUtil.readCache(cacheName);
            if (o != null) {
                return (Page) o;
            }
        }

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

        if (name == null) {
            CacheUtil.saveCache(cacheName, page);
        }
        return page;
    }

    @Override
    public SetmealDto getById(Long id) {
        String cacheName = "setMeal_getById_"+ id;
        Object o = CacheUtil.readCache(cacheName);
        if (o != null) {
            return (SetmealDto) o;
        }

        SetmealDto dto = mapper.getById(id);
        List<SetmealDish> list = setMealDishService.getDishList(id);
        dto.setSetmealDishes(new ArrayList<>(list));

        CacheUtil.saveCache(cacheName, dto);
        return dto;
    }

    @Override
    public void update(SetmealDto dto) {
        CacheUtil.clearCache("setMeal_*");

        dto.setUpdateTime(new Date());
        dto.setUpdateUser(BaseContext.getEmpId());
        if (BaseContext.getEmpId() == null) {
            throw new CustomException("登录状态异常，请尝试重新登录！");
        }
        mapper.updateById(dto);
    }

    @Override
    public List<Setmeal> getListByCategoryId(Long categoryId) {
        String cacheName = "setMeal_getListByCategoryId_" + categoryId;
        Object o = CacheUtil.readCache(cacheName);
        if (o != null) {
            return (List<Setmeal>) o;
        }


        List<Setmeal> result = mapper.getList(categoryId);

        CacheUtil.saveCache(cacheName,result);
        return result;
    }

    @Override
    public List<Dish> getDishById(Long id) {
        String cacheName = "setMeal_getDishById_" + id;
        Object o = CacheUtil.readCache(cacheName);
        if (o != null) {
            return (List<Dish>) o;
        }

        List<Dish> list = setMealDishService.getDishAndImage(id);

        CacheUtil.saveCache(cacheName, list);
        return list;

    }
}
