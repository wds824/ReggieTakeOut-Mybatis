package com.wds.service.impl;

import cn.hutool.core.util.IdUtil;
import com.wds.common.BaseContext;
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
        List<AddressBook> list = mapper.getList(userId);
        return list;
    }

    @Override
    public void setDefault(Long id) {
        mapper.closeDefault(BaseContext.getUserId());
        mapper.setDefault(id);
    }

    @Override
    public AddressBook getById(Long id) {
        return mapper.getById(id);

    }

    @Override
    public void remove(Long ids) {
        mapper.remove(ids);
    }

    @Override
    public void update(AddressBook book) {
        book.setUpdateTime(new Date());
        book.setUpdateUser(BaseContext.getUserId());
        mapper.update(book);
    }

    @Override
    public void save(AddressBook book) {
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
        return mapper.getDefault(BaseContext.getUserId());
    }
}
