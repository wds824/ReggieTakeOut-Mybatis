package com.wds.controller;

import com.wds.common.BaseContext;
import com.wds.common.JsonResult;
import com.wds.entity.ShoppingCart;
import com.wds.exception.CustomException;
import com.wds.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-30 14:49
 */
@RestController
@Slf4j
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService service;

    @GetMapping("/list")
    public JsonResult getList() {
        Long userId = BaseContext.getUserId();
        if (userId == null) {
            throw new CustomException("用户登录状态已过期，请重新登录。");
        }
        List<ShoppingCart> result = service.getListByUserId(userId);
        result.removeIf(cart -> cart.getNumber() == 0);
        return JsonResult.ok(result);
    }

    @PostMapping("/add")
    public JsonResult add(@RequestBody ShoppingCart item) {
        //判断是不是第一次加
        if (BaseContext.getUserId() == null) {
            throw new CustomException("用户状态异常，请重新登录。");
        }

        synchronized (BaseContext.getUserId().toString().getBytes(StandardCharsets.UTF_8)) {
            ShoppingCart cart = service.getByDishId(item.getDishId(), item.getSetmealId());
            if (cart == null) {
                //第一次添加
                service.addItem(item);
            } else {
                //重复添加 number ++
                service.updateNumberById(cart.getId());
                cart.setNumber(cart.getNumber() + 1);
                return JsonResult.ok(cart);
            }

        }
        item.setNumber(1);
        return JsonResult.ok(item);
    }

    @PostMapping("/sub")
    public JsonResult sub(@RequestBody ShoppingCart cart) {
        service.sub(cart.getDishId(), cart.getSetmealId());
        cart = service.getByDishId(cart.getDishId(), cart.getSetmealId());
        return JsonResult.ok(cart);
    }

    @DeleteMapping("/clean")
    public JsonResult clean() {
        service.cleanCurrentUserCart();
        return JsonResult.ok();
    }


}
