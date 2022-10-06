package com.wds.service;

import com.wds.dto.Page;
import com.wds.entity.Employee;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-21 9:32
 */
public interface EmployeeService  {
    Employee getLoginEmployee(Employee employee);

    Page getPage(int page, int pageSize, String name);

    Employee getById(Long id);

    void updateById(Employee employee);

    void addEmployee(Employee employee);
}
