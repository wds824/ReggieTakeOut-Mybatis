package com.wds.service.impl;


import cn.hutool.core.util.IdUtil;
import com.wds.common.BaseContext;
import com.wds.common.Utils.CacheUtil;
import com.wds.dto.Page;
import com.wds.entity.Employee;
import com.wds.exception.CustomException;
import com.wds.mapper.EmployeeMapper;
import com.wds.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-21 9:33
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    @Override
    public Employee getLoginEmployee(Employee employee) {
        employee = mapper.login(employee.getUsername(), employee.getPassword());
        return employee;
    }

    @Override
    public Page getPage(int page, int pageSize, String name) {
        String cacheName = null;
        if (name == null) {
            cacheName = "employee_getPage_" + page + "_" + pageSize;
            Object o = CacheUtil.readCache(cacheName);
            if (o != null) {
                return (Page) o;
            }
        }

        //处理模糊查询
        if (name != null) {
            name = '%' + name + '%';
        }

        Page result = new Page();
        result.setCurrent(page);
        result.setSize(pageSize);
        //第一次查询 pageSize条记录

        List<Employee> records = mapper.getPage((page - 1) * pageSize, pageSize, name); //(偏移，记录数)
        result.setRecords(new ArrayList<>(records)); //格式转换

        //第二次查询查询记录总数 并计算页数
        int count = mapper.getCount(name);
        result.setPages((double) count / pageSize == 0 ? count / pageSize : count / pageSize + 1);
        result.setTotal(count);

        if (name == null) {
            CacheUtil.saveCache(cacheName, result);
        }
        return result;
    }

    @Override
    public Employee getById(Long id) {
        String cacheName = "employee_getById_" + id;
        Object o = CacheUtil.readCache(cacheName);
        if (o != null) {
            return (Employee) o;
        }

        Employee byId = mapper.getById(id);

        CacheUtil.saveCache(cacheName, o);
        return byId;
    }

    @Override
    public void updateById(Employee employee) {
        CacheUtil.clearCache("employee_*");

        employee.setUpdateTime(new Date());
        mapper.updateById(employee);
    }

    @Override
    public void addEmployee(Employee employee) {
        CacheUtil.clearCache("employee_*");

        //创建用户和修改用户
        if (BaseContext.getEmpId() == null) {
            throw new CustomException("员工账户状态异常，请重新登录。");
        }
        employee.setCreateUser(BaseContext.getEmpId());
        employee.setUpdateUser(BaseContext.getEmpId());
        //雪花算法生成id
        employee.setId(IdUtil.getSnowflakeNextId());
        //默认密码123456
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
        //创建时间和更新时间
        Date now = new Date();
        employee.setCreateTime(now);
        employee.setUpdateTime(now);
        //默认启用状态
        employee.setStatus(1);
        mapper.save(employee);
    }
}
