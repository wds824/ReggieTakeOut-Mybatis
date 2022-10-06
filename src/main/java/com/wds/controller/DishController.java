package com.wds.controller;

import com.wds.common.JsonResult;
import com.wds.common.Utils.RedisUtil;
import com.wds.dto.DishDto;
import com.wds.dto.Page;
import com.wds.entity.DishFlavor;
import com.wds.service.DishFlavorService;
import com.wds.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-25 16:51
 */
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService service;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 缓存机制: 在 name == null 的时候使用缓存，减少冗余缓存
     */
    @GetMapping("/page")
    public JsonResult getPage(int page, int pageSize, String name) {
        String cacheName = "dish_page_" + page + "_" + pageSize; //cache name
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();  //redis ops
        if (name == null) {
            //    读取缓存
            Object o = ops.get(cacheName);
            if (o != null) {
                log.info("读取缓存: {}", cacheName);
                return JsonResult.ok(o);
            }

        }

        Page result = service.getPage(page, pageSize, name);

        //   创建缓存
        if (name == null) {
            log.info("创建缓存: {}", cacheName);
            ops.set(cacheName, result);
        }

        return JsonResult.ok(result);
    }

    /**
     * 修改菜品的回显
     * <p>
     * redis: update前操作，不进行缓存
     */
    @GetMapping("/{id}")
    public JsonResult getById(@PathVariable Long id) {

        DishDto dto = service.getById(id);
        List<DishFlavor> dishFlavors = dishFlavorService.getFlavors(id);
        dto.setFlavors(dishFlavors);
        return JsonResult.ok(dto);
    }

    /**
     * update 操作 清除dish 的缓存
     */
    @PutMapping
    public JsonResult update(@RequestBody DishDto dto) {
        RedisUtil.clearCache("dish_*", redisTemplate);

        service.update(dto);
        dishFlavorService.updateFlavors(dto);
        return JsonResult.ok();
    }

    /**
     * @param status 对应的
     * @param ids    id
     */
    @PostMapping("/status/{status}")
    public JsonResult updateStatus(@PathVariable int status, Long[] ids) {
        RedisUtil.clearCache("dish_*", redisTemplate);

        List<Long> idList = Arrays.asList(ids);
        service.updateStatus(idList, status);
        return JsonResult.ok();
    }


    /**
     * 保存新的菜品，并保存其口味
     *
     * @param dto 菜品信息 && 口味信息
     */
    @PostMapping
    public JsonResult save(@RequestBody DishDto dto) {
        RedisUtil.clearCache("dish_*", redisTemplate);


        service.save(dto);
        dishFlavorService.saveFlavorsFromDto(dto);
        return JsonResult.ok();
    }

    /**
     * 删除菜品
     * 1. 删除dish表的记录
     * 2. 删除dish_flavor表的记录
     *
     * @param ids 要删除的dish_id
     * @return ok
     */
    @DeleteMapping
    public JsonResult remove(Long[] ids) {
        RedisUtil.clearCache("dish_*", redisTemplate);


        List<Long> list = Arrays.asList(ids);
        service.removeById(list);
        dishFlavorService.removeByDishId(list);
        return JsonResult.ok();
    }

    /**
     * 开启缓存
     */
    @GetMapping("/list")
    public JsonResult list(Long categoryId) {
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        String cacheName = "dish_list_" + categoryId;
//read
        Object o = ops.get(cacheName);
        if (o != null) {
            log.info("读取缓存: {}", cacheName);
            return JsonResult.ok(o);
        }

        List<DishDto> dishDtos = service.getByCategoryId(categoryId);
        for (DishDto dto : dishDtos) {
            dto.setFlavors(dishFlavorService.getFlavors(dto.getId()));
        }
//write
        ops.set(cacheName, dishDtos);
        log.info("写入缓存: {}", cacheName);
        return JsonResult.ok(dishDtos);
    }

    /**
     * 清空 dish下的缓存
     */
//    private void clearDishCache() {
//        Set<Object> keys = redisTemplate.keys("dish_*");
//        if (keys != null) {
//            log.info("清空缓存： {}", "dish_*");
//            redisTemplate.delete(keys);
//        }
//    }

}