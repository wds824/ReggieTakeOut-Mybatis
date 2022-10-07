package com.wds.service.impl;

import cn.hutool.core.util.IdUtil;
import com.wds.common.BaseContext;
import com.wds.common.Utils.CacheUtil;
import com.wds.entity.AddressBook;
import com.wds.mapper.AddressBookMapper;
import com.wds.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-01 17:08
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {
    @Autowired
    private AddressBookMapper mapper;

    @Override
    public List<AddressBook> getList(Long userId) {
        String cacheName = "addressBook_getList_" + userId;
        Object o = CacheUtil.readCache(cacheName);
        if (o != null) {
            return (List<AddressBook>) o;
        }

        List<AddressBook> list = mapper.getList(userId);

        CacheUtil.saveCache(cacheName, list);
        return list;
    }

    @Override
    public void setDefault(Long id) {
        CacheUtil.clearCache("addressBook_*");

        mapper.closeDefault(BaseContext.getUserId());
        mapper.setDefault(id);
    }

    @Override
    public AddressBook getById(Long id) {
        String cacheName = "addressBook_getById_" + id;
        Object o = CacheUtil.readCache(cacheName);
        if (o != null) {
            return (AddressBook) o;
        }

        AddressBook book = mapper.getById(id);

        CacheUtil.saveCache(cacheName, book);
        return book;
    }

    @Override
    public void remove(Long ids) {
        CacheUtil.clearCache("addressBook_*");

        mapper.remove(ids);
    }

    @Override
    public void update(AddressBook book) {
        CacheUtil.clearCache("addressBook_*");

        book.setUpdateTime(new Date());
        book.setUpdateUser(BaseContext.getUserId());
        mapper.update(book);
    }

    @Override
    public void save(AddressBook book) {
        CacheUtil.clearCache("addressBook_*");

        book.setId(IdUtil.getSnowflakeNextId());

        Long user = BaseContext.getUserId();
        book.setUserId(user);
        book.setUpdateUser(user);
        book.setCreateUser(user);

        Date now = new Date();
        book.setUpdateTime(now);
        book.setCreateTime(now);

        mapper.save(book);
    }

    @Override
    public AddressBook getDefault() {
        String cacheName = "addressBook_getDefault";
        Object o = CacheUtil.readCache(cacheName);
        if (o != null) {
            return (AddressBook) o;
        }

        AddressBook book = mapper.getDefault(BaseContext.getUserId());

        CacheUtil.saveCache(cacheName,book);
        return book;
    }
}
