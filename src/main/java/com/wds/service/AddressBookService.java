package com.wds.service;

import com.wds.entity.AddressBook;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-01 17:08
 */
public interface AddressBookService {
    List<AddressBook> getList(Long userId);

    void setDefault(Long id);

    AddressBook getById(Long id);

    void remove(Long ids);

    void update(AddressBook book);

    void save(AddressBook book);

    AddressBook getDefault();

}
