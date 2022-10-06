package com.wds.service;

import com.wds.entity.User;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-29 19:45
 */
public interface UserService {
    User getUserByPhone(User user);

    void save(User result);
}
