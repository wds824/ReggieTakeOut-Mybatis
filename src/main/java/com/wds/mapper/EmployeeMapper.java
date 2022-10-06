package com.wds.mapper;

import com.wds.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-21 7:41
 */
@Mapper
public interface EmployeeMapper {

    @Deprecated
    List<Employee> getAll();

    Employee getById(Long id);

    Employee login(String username, String password);

    List<Employee> getPage(int page, int pageSize, String name);

    Integer getCount(String name);

    void updateById(Employee employee);

    void save(Employee employee);

}
