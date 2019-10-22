package com.gaofv.controller;

import com.gaofv.entity.Result;
import com.gaofv.entity.User;
import com.gaofv.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: GF
 * @Date: 2019/10/21
 * @Description: com.gaofv.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("login")
    public Result login(String loginName,String password){
        return userService.login(loginName,password);
    }
    @PostMapping("regist")
    public Result regist(User user){
        return userService.regist(user);
    }

    @RequestMapping(value = "findAll",method = RequestMethod.POST)
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "findByName",method = RequestMethod.POST)
    public User findByName(String loginName){
        return userService.findByName(loginName);
    }

    @RequestMapping(value = "remove",method = RequestMethod.POST)
    public void remove(long id){
        userService.remove(id);
    }
}
