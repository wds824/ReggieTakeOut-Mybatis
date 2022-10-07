package com.wds.service.impl;

import cn.hutool.core.util.IdUtil;
import com.wds.common.BaseContext;
import com.wds.common.Utils.RedisUtil;
import com.wds.dto.DishDto;
import com.wds.dto.Page;
import com.wds.exception.CustomException;
import com.wds.mapper.DishMapper;
import com.wds.service.DishService;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2022-09-25 18:25
 */
@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper mapper;



    /**
     * 缓存机制: 在 name == null 的时候使用缓存，减少冗余缓存
     */
    @Override
    public Page getPage(int page, int pageSize, String name) {
        String cacheName = "dish_getPage_" + page + "_" + pageSize; //cache name

        if (name == null) {
            //    读取缓存

            Object o = RedisUtil.readCache(cacheName);
            if (o != null) {
                log.info("读取缓存: {}", cacheName);
                return (Page) o;
            }
        }


        if (name != null) {
            name = "%" + name + "%";
        }
        List<DishDto> list = mapper.getPage((page - 1) * pageSize, pageSize, name);
        int count = mapper.getCount();

        Page result = new Page();
        result.setRecords(new ArrayList<>(list));
        result.setTotal(count);

        result.setSize(pageSize);
        result.setPages((double) count / pageSize == 0 ? count / pageSize : count / pageSize + 1);
        result.setCurrent(page);

        //   创建缓存
        if (name == null) {
            RedisUtil.saveCache(cacheName, result);
        }
        return result;
    }

    /**
     * enable cache
     */
    @Override
    public DishDto getById(Long id) {
        String cacheName = "dish_getById_" + id;
        Object result = RedisUtil.readCache(cacheName);
        if (result != null) {
            return (DishDto) result;
        }

        DishDto dto = mapper.getById(id);

        RedisUtil.saveCache(cacheName, dto);
        return dto;
    }

    @Override
    public void update(DishDto dto) {
        RedisUtil.clearCache("dish_*");

        dto.setUpdateTime(new Date());
        dto.setUpdateUser(BaseContext.getEmpId());
        if (BaseContext.getEmpId() == null) {
            throw new CustomException("用户登录状态异常，请重新登录后再试。");
        }

        mapper.update(dto);
    }

    @Override
    public void updateStatus(List<Long> ids, int status) {
        RedisUtil.clearCache("dish_*");

        mapper.updateStatus(ids, status);
    }

    @Override
    public void save(DishDto dto) {
        RedisUtil.clearCache("dish_*");

        dto.setId(IdUtil.getSnowflakeNextId());
        dto.setCreateTime(new Date());
        dto.setCreateUser(BaseContext.getEmpId());
        mapper.save(dto);
    }

    @Override
    public void removeById(List<Long> list) {
        RedisUtil.clearCache("dish_*");

        mapper.delete(list);
    }

    @Override
    public List<DishDto> getByCategoryId(Long categoryId) {
        String cacheName = "dish_getByCategoryId_" + categoryId;
//read
        Object o = RedisUtil.readCache(cacheName);
        if (o != null) {

            return (List<DishDto>) o;
        }

        List<DishDto> list = mapper.getByCategoryId(categoryId);

//write
        RedisUtil.saveCache(cacheName, list);
        return list;
    }
}
