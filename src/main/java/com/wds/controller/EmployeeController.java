package com.wds.controller;

import com.wds.common.JsonResult;
import com.wds.dto.Page;
import com.wds.entity.Employee;
import com.wds.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-21 9:14
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public JsonResult login(@RequestBody Employee employee, HttpServletRequest request) {
        //密码 md5加密
        employee.setPassword(
                DigestUtils.md5DigestAsHex(employee.getPassword().getBytes(StandardCharsets.UTF_8)));
        //查询
        employee = employeeService.getLoginEmployee(employee);
        //存储id
        if (employee != null) {
            request.getSession().setAttribute("empId", employee.getId());
            employee.setPassword("");
            return JsonResult.ok(employee);
        }
        return JsonResult.error("账号或密码错误");
    }

    /**
     * 分页查询
     * @param page 查询的页码
     * @param pageSize 每页打小
     */
    @GetMapping("/page")
    public JsonResult page(int page,int pageSize,String name){
        Page result = employeeService.getPage(page,pageSize,name);
        return JsonResult.ok(result);
    }

    @GetMapping("/{id}")
    public JsonResult byId(@PathVariable Long id){
        Employee employee = employeeService.getById(id);

        return JsonResult.ok(employee);
    }

    @PutMapping
    public JsonResult update(@RequestBody Employee employee){
        employeeService.updateById(employee);
        return JsonResult.ok();
    }

    @PostMapping
    public JsonResult saveEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return JsonResult.ok();
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public JsonResult logout(HttpServletRequest request){
        request.getSession().setAttribute("empId", null);
        return JsonResult.ok();
    }
}

