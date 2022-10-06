package com.wds.service.impl;

import cn.hutool.core.util.IdUtil;
import com.wds.common.BaseContext;
import com.wds.dto.DishDto;
import com.wds.dto.Page;
import com.wds.entity.Category;
import com.wds.exception.CustomException;
import com.wds.mapper.DishMapper;
import com.wds.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-25 18:25
 */
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper mapper;

    @Override
    public Page getPage(int page, int pageSize, String name) {
        if (name != null) {
            name = "%" + name + "%";
        }
        List<DishDto> list = mapper.getPage((page - 1) * pageSize, pageSize, name);
        int count = mapper.getCount();

        Page result = new Page();
        result.setRecords(new ArrayList<>(list));
        result.setTotal(count);

        result.setSize(pageSize);
        result.setPages((double) count / pageSize == 0 ? count / pageSize : count / pageSize + 1);
        result.setCurrent(page);

        return result;
    }

    @Override
    public DishDto getById(Long id) {
        DishDto dto = mapper.getById(id);
        return dto;
    }

    @Override
    public void update(DishDto dto) {

        dto.setUpdateTime(new Date());
        dto.setUpdateUser(BaseContext.getEmpId());
        if (BaseContext.getEmpId() == null){
            throw new CustomException("用户登录状态异常，请重新登录后再试。");
        }

        mapper.update(dto);
    }

    @Override
    public void updateStatus(List<Long> ids, int status) {
        mapper.updateStatus(ids,status);
    }

    @Override
    public void save(DishDto dto) {
        dto.setId(IdUtil.getSnowflakeNextId());
        dto.setCreateTime(new Date());
        dto.setCreateUser(BaseContext.getEmpId());
        mapper.save(dto);
    }

    @Override
    public void removeById(List<Long> list) {
        mapper.delete(list);
    }

    @Override
    public List<DishDto> getByCategoryId(Long categoryId) {
        List<DishDto> list = mapper.getByCategoryId(categoryId);
        return list;
    }
}
