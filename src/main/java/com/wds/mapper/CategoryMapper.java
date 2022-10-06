package com.wds.mapper;

import com.wds.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-23 18:30
 */
@Mapper
public interface CategoryMapper {

    List<Category> page(int page, int pageSize);

    int count();

    void update(Category category);

    void save(Category category);

    void delete(Long id);

    List<Category> getList(Integer type);
}
