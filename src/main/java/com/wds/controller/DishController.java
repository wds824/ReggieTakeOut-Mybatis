package com.wds.controller;

import com.wds.common.JsonResult;
import com.wds.dto.DishDto;
import com.wds.dto.Page;
import com.wds.entity.DishFlavor;
import com.wds.service.DishFlavorService;
import com.wds.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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



    /**
     *   分页查询
     */
    @GetMapping("/page")
    public JsonResult getPage(int page, int pageSize, String name) {

        Page result = service.getPage(page, pageSize, name);
        return JsonResult.ok(result);
    }

    /**
     * 修改菜品的回显
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


        List<Long> list = Arrays.asList(ids);
        service.removeById(list);
        dishFlavorService.removeByDishId(list);
        return JsonResult.ok();
    }

    /**
     * 前台调用
     */
    @GetMapping("/list")
    public JsonResult list(Long categoryId) {


        List<DishDto> dishDtos = service.getByCategoryId(categoryId);
        for (DishDto dto : dishDtos) {
            dto.setFlavors(dishFlavorService.getFlavors(dto.getId()));
        }

        return JsonResult.ok(dishDtos);
    }

    /*
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