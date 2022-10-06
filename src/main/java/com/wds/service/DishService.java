package com.wds.service;

import com.wds.dto.DishDto;
import com.wds.dto.Page;
import com.wds.entity.Category;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-25 18:25
 */
public interface DishService {

    Page getPage(int page, int pageSize, String name);

    DishDto getById(Long id);

    void update(DishDto dto);

    void updateStatus(List<Long> ids, int status);

    void save(DishDto dto);

    void removeById(List<Long> list);

    List<DishDto> getByCategoryId(Long categoryId);
}
