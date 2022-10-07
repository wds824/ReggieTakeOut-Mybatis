package com.wds.service.impl;

import com.wds.common.Utils.CacheUtil;
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
        String cacheName = "user_getByPhone_" + user.getPhone();
        Object o = CacheUtil.readCache(cacheName);
        if (o != null) {
            return (User) o;
        }

        User result = mapper.getUserByPhone(user.getPhone());

        CacheUtil.saveCache(cacheName, result);
        return result;
    }

    @Override
    public void save(User result) {
        CacheUtil.clearCache("user_*");
        mapper.save(result);
    }
}
