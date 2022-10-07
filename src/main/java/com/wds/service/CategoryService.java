package com.wds.service;

import com.wds.dto.Page;
import com.wds.entity.Category;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-23 18:38
 */
public interface CategoryService {
    Page page(int page, int pageSize);

    void update(Category category);

    void save(Category category);

    void delete(Long id);

    List<Category> getList(int type);
}
