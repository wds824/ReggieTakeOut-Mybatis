package com.wds.service.impl;

import cn.hutool.core.util.IdUtil;
import com.wds.common.BaseContext;
import com.wds.common.Utils.CacheUtil;
import com.wds.dto.Page;
import com.wds.entity.Category;
import com.wds.mapper.CategoryMapper;
import com.wds.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-23 18:38
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper mapper;

    @Override
    public Page page(int page, int pageSize) {
        String cacheName = "category_Page_" + page + "_" + pageSize;
        Object o = CacheUtil.readCache(cacheName);
        if (o != null) {
            return (Page) o;
        }


        int count = mapper.count();
        List<Category> categories = mapper.page((page - 1) * pageSize, pageSize);

        Page result = new Page();

        result.setTotal(count);
        result.setRecords(new ArrayList<>(categories));
        result.setPages((double) count / pageSize == 0 ? count / pageSize : count / pageSize + 1);
        result.setCurrent(page);
        result.setSize(pageSize);

        //write
        CacheUtil.saveCache(cacheName, result);

        return result;
    }

    /**
     * 更新 name sort
     * 自动更新 updateTime updateUser
     */
    @Override
    public void update(Category category) {
        CacheUtil.clearCache("category_*");

        category.setUpdateUser(BaseContext.getEmpId());
        category.setUpdateTime(new Date());
        mapper.update(category);
    }

    @Override
    public void save(Category category) {
        CacheUtil.clearCache("category_*");


        category.setId(IdUtil.getSnowflakeNextId());
        Date now = new Date();
        Long empId = BaseContext.getEmpId();
        category.setCreateTime(now);
        category.setCreateUser(empId);

        category.setUpdateTime(now);
        category.setUpdateUser(empId);
        mapper.save(category);
    }

    @Override
    public void delete(Long id) {
        CacheUtil.clearCache("category_*");

        mapper.delete(id);
    }

    @Override
    public List<Category> getList(Integer type) {
        String cacheName = "category_getList_" + type;
        Object o = CacheUtil.readCache(cacheName);
        if (o != null) {
            return (List<Category>) o;
        }


        List<Category> list = mapper.getList(type);

        CacheUtil.saveCache(cacheName, list);
        return list;
    }
}
