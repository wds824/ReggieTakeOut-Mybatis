package com.wds.mapper;

import com.wds.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-29 19:44
 */
@Mapper
public interface UserMapper {
    User getUserByPhone(String phone);

    void save(User user);
}
