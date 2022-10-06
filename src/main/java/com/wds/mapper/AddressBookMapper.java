package com.wds.mapper;

import com.wds.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-01 17:05
 */
@Mapper
public interface AddressBookMapper {
    List<AddressBook> getList(Long userId);

    void closeDefault(Long userId);

    void setDefault(Long id);

    AddressBook getById(Long id);

    void remove(Long id);

    void update(AddressBook book);

    void save(AddressBook book);

    AddressBook getDefault(Long userId);

}
