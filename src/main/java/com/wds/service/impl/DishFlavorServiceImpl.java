package com.wds.service.impl;

import cn.hutool.core.util.IdUtil;
import com.wds.common.BaseContext;
import com.wds.common.Utils.RedisUtil;
import com.wds.dto.DishDto;
import com.wds.entity.DishFlavor;
import com.wds.mapper.DishFlavorMapper;
import com.wds.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-26 16:55
 */
@Service
public class DishFlavorServiceImpl implements DishFlavorService {
    @Autowired
    private DishFlavorMapper mapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public List<DishFlavor> getFlavors(Long id) {
        if (id == null) {
            return null;
        }

        String cacheName = "dishFlavor_get_" + id;
        Object o = RedisUtil.readCache(cacheName);
        if (o != null) {
            return (List<DishFlavor>) o;
        }

        List<DishFlavor> dishFlavors = mapper.getFlavorsByDishId(id);

        // write cache
        RedisUtil.saveCache(cacheName, dishFlavors);

        return new ArrayList<>(dishFlavors);
    }

    @Override
    public void updateFlavors(DishDto dto) {
        RedisUtil.clearCache("dishFlavor_*");

        mapper.removeFlavorsByDishId(dto.getId());
        List<DishFlavor> flavors = dto.getFlavors();
        if (flavors.size() != 0) {
            //为口味创建新的 id
            for (DishFlavor flavor : flavors) {
                flavor.setId(IdUtil.getSnowflakeNextId());
            }
            mapper.insertFlavors(flavors, dto.getId(), new Date(), BaseContext.getEmpId()); // dish_id && time && user
        }
    }

    @Override
    public void saveFlavorsFromDto(DishDto dto) {
        RedisUtil.clearCache("dishFlavor_*");

        List<DishFlavor> flavors = dto.getFlavors();
        for (DishFlavor flavor : flavors) {
            flavor.setId(IdUtil.getSnowflakeNextId());
        }
        mapper.insertFlavors(flavors, dto.getId(), new Date(), BaseContext.getEmpId());
    }

    @Override
    public void removeByDishId(List<Long> list) {
        RedisUtil.clearCache("dishFlavor_*");

        mapper.deleteByDishIds(list);
    }
}