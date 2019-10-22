package com.gaofv.service.impl;

import com.gaofv.entity.Result;
import com.gaofv.entity.User;
import com.gaofv.mapper.UserMapper;
import com.gaofv.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Auther: GF
 * @Date: 2019/10/20
 * @Description: com.gaofv.service.impl
 * @version: 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserMapper userMapper;
    @Override
    public Result regist(User user) {
        System.out.println("============ "+user);
        if(user.getLoginName().length()<4||user.getPassword().length()>20){
            return new Result(false,"登陆名最短 4 个字符，最长 20 个字符");
        }
        if(user.getPassword().length()<6||user.getPassword().length()>20){
            return new Result(false,"密码最短 6 个字符，最长 20 个字符");
        }
        if(!Pattern.matches("^(0|1){1}$",user.getSex())){
            return new Result(false,"性别只能是 1（男）或者 0（女）");
        }
        if(!Pattern.matches("^(?:\\+?86)?1(?:3(?:4[^9\\D]|[5-9]\\d)|5[^3-6\\D]\\d|8[23478]\\d|(?:78|98)\\d)\\d{7}$",user.getPhone())){
            return new Result(false,"手机号格式错误");
        }
        User userSelected = userMapper.selectByName(user.getLoginName());
        if(!StringUtils.isEmpty(userSelected)){
            return new Result(false,"当前用户名已存在");
        }
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        userMapper.insert(user);
        return new Result(true,"注册成功");
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Result login(String loginName, String password){
        User userSelected = userMapper.selectByName(loginName);
        if(StringUtils.isEmpty(userSelected)){
            return new Result(false,"用户名不存在");
        }
        if(!password.equals(userSelected.getPassword())){
            return new Result(false,"密码输入错误");
        }

        //使用jedisTemplate遍历redis中的user中是否对应用户名

        return new Result(true,"登录成功");
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findByName(String loginName) {
        return userMapper.selectByName(loginName);
    }

    @Override
    public void remove(Long id) {
        userMapper.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return userMapper.selectAll();
    }
}
