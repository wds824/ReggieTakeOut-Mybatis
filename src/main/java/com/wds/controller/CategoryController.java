package com.wds.controller;

import com.wds.common.JsonResult;
import com.wds.dto.Page;
import com.wds.entity.Category;
import com.wds.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-23 18:33
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     *  分页查询
     */
    @GetMapping("/page")
    public JsonResult page(int page, int pageSize){
        Page result = categoryService.page(page,pageSize);
        return JsonResult.ok(result);
    }

    /**
     *  根据id 更新分类名称和排序
     */
    @PutMapping()
    public JsonResult update(@RequestBody Category category){
        categoryService.update(category);
        return JsonResult.ok();
    }

    /**
     *  新增菜品
     */
    @PostMapping
    public JsonResult save(@RequestBody Category category){
        categoryService.save(category);
        return JsonResult.ok();
    }

    /**
     *  根据ID删除分类
     */
    @DeleteMapping()
    public JsonResult remove(Long id){
        categoryService.delete(id);
        return JsonResult.ok();
    }

    @GetMapping("/list")
    public JsonResult list(Integer type){
        List<Category> list =  categoryService.getList(type);
        return JsonResult.ok(list);
    }

}
