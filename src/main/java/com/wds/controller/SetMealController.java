package com.wds.controller;

import com.wds.common.JsonResult;
import com.wds.dto.Page;
import com.wds.dto.SetmealDto;
import com.wds.entity.Dish;
import com.wds.entity.Setmeal;
import com.wds.service.SetMealDishService;
import com.wds.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-28 15:15
 */
@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Autowired
    private SetMealService service;

    @Autowired
    private SetMealDishService setMealDishService;

    @GetMapping("/page")
    public JsonResult getPage(int page, int pageSize, String name) {
        Page result = service.getPage(page, pageSize, name);
        return JsonResult.ok(result);
    }
    @GetMapping("/dish/{id}")
    public JsonResult getOne(@PathVariable Long id){
        List<Dish> list = service.getDishById(id);
        return JsonResult.ok(list);
    }
    @GetMapping("/{id}")
    public JsonResult reShow(@PathVariable Long id){
        SetmealDto dto = service.getById(id);
        return JsonResult.ok(dto);
    }

    @PutMapping
    public JsonResult update(@RequestBody SetmealDto dto){
        service.update(dto);
        setMealDishService.updateSetMealDish(dto);
        return JsonResult.ok();
    }

    @GetMapping("/list")
    public JsonResult getList(Long categoryId){
        List<Setmeal> result = service.getListByCategoryId(categoryId);
        return JsonResult.ok(result);
    }

}
