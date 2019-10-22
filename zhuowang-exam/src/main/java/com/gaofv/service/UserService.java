package com.gaofv.service;

import com.gaofv.entity.Result;
import com.gaofv.entity.User;

import java.util.List;


/**
 * @Auther: GF
 * @Date: 2019/10/20
 * @Description: com.gaofv.service
 * @version: 1.0
 */
public interface UserService {
    Result regist(User user);

    Result login(String loginName, String password);

    User findByName(String loginName);

    void remove(Long id);

    List<User> findAll();
}
