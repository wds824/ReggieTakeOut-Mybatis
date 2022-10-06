package com.wds.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.db.sql.Order;
import com.wds.common.BaseContext;
import com.wds.common.JsonResult;
import com.wds.dto.Page;
import com.wds.entity.AddressBook;
import com.wds.entity.OrderDetail;
import com.wds.entity.Orders;
import com.wds.entity.ShoppingCart;
import com.wds.exception.CustomException;
import com.wds.service.AddressBookService;
import com.wds.service.OrderDetailService;
import com.wds.service.OrdersService;
import com.wds.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-06 8:47
 */
@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/page")
    public JsonResult getPage(int page, int pageSize, Long number, String beginTime, String endTime) {
        Date begin = null;
        Date end = null;
        if (beginTime != null && endTime != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                begin = format.parse(beginTime);
                end = format.parse(endTime);
            } catch (ParseException e) {
                throw new CustomException("日期字符串格式异常，请检查输入。");
            }
        }

        Page result = ordersService.getPage(page, pageSize, number, begin, end);

        return JsonResult.ok(result);
    }

    @GetMapping("/userPage")
    public JsonResult getUserPage(int page, int pageSize) {
        Page result = ordersService.getUserPage(page, pageSize);
        return JsonResult.ok(result);
    }

    @PostMapping("/submit")
    @Transactional
    public JsonResult submit(@RequestBody Orders order) {
        AddressBook book = addressBookService.getById(order.getAddressBookId());
        // 读地址蒲 赋值地址信息
        long id = IdUtil.getSnowflakeNextId();
        // 设置id参数
        order.setId(id);
        order.setNumber(id + "");
        order.setAddress(book.getDetail());
        Date now = new Date();
        order.setOrderTime(now);
        order.setCheckoutTime(now);
        order.setConsignee(book.getConsignee());
        order.setUserId(BaseContext.getUserId());
        order.setPhone(book.getPhone());
        order.setStatus(2);

        // 读购物车
        List<ShoppingCart> cart = shoppingCartService.getListByUserId(BaseContext.getUserId());
        BigDecimal amount = new BigDecimal("0.0");
        List<OrderDetail> details = new ArrayList<>();

        for (ShoppingCart shoppingCart : cart) {
            BigDecimal price = shoppingCart.getAmount().multiply(new BigDecimal(shoppingCart.getNumber()));
            amount = amount.add(price);

            OrderDetail detail = new OrderDetail();
            detail.setId(IdUtil.getSnowflakeNextId());
            detail.setName(shoppingCart.getName());
            detail.setImage(shoppingCart.getImage());
            detail.setOrderId(id);
            detail.setSetmealId(shoppingCart.getSetmealId());
            detail.setAmount(price);
            detail.setDishFlavor(shoppingCart.getDishFlavor());
            detail.setDishId(shoppingCart.getDishId());
            details.add(detail);
        }
        order.setAmount(amount);
        // 存储订单详情
        orderDetailService.save(details);
        // 清空购物车
        shoppingCartService.cleanCurrentUserCart();
        // 存订单
        ordersService.saveOrder(order);

        return JsonResult.ok();
    }
    @PutMapping
    public JsonResult updateStatus(Orders orders){
        ordersService.updateStatus(orders);
        return null;
    }
}
