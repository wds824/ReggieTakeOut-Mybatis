package com.wds.mapper;

import com.wds.dto.DishDto;
import com.wds.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-25 16:32
 */
@Mapper
public interface DishMapper {
    List<DishDto> getPage(int page, int pageSize, String name);

    int getCount();

    DishDto getById(Long id);

    void update(DishDto dto);

    void updateStatus(List<Long> ids, int status);

    void save(DishDto dto);

    void delete(List<Long> list);

    List<DishDto> getByCategoryId(Long categoryId);
}
