package com.wds.controller;

import cn.hutool.core.util.IdUtil;
import com.wds.common.BaseContext;
import com.wds.common.JsonResult;
import com.wds.entity.User;
import com.wds.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-29 19:45
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    /**
     *  登录
     *  自动依据手机号 创建用户
     *  存储id
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody User user, HttpSession session){
        User result = service.getUserByPhone(user);
        if (result == null) {
            result = new User();
            result.setId(IdUtil.getSnowflakeNextId());
            result.setName("新用户");
            result.setIdNumber("111222333444555666");
            result.setPhone(user.getPhone());
            service.save(result);
        }
        session.setAttribute("userId", result.getId());
        return JsonResult.ok(result);
    }

    /**
     * 登出 清除session的id值
     */
    @PostMapping("/loginout")
    public JsonResult logout(HttpSession session){
        session.removeAttribute("userId");
        return JsonResult.ok();
    }
}
