package com.wds.service.impl;

import com.wds.entity.User;
import com.wds.mapper.UserMapper;
import com.wds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-29 19:45
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public User getUserByPhone(User user) {
        User result = mapper.getUserByPhone(user.getPhone());
        return result;
    }

    @Override
    public void save(User result) {
        mapper.save(result);
    }
}
