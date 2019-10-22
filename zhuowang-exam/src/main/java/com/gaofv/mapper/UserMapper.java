package com.gaofv.mapper;

import com.gaofv.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: GF
 * @Date: 2019/10/20
 * @Description: com.gaofv.mapper
 * @version: 1.0
 */
public interface UserMapper {
    @Select("select * from user")
    @Results({
        @Result(property = "loginName",column = "login_name"),
        @Result(property = "gmtCreate",column = "gmt_create"),
        @Result(property = "gmtModified",column = "gmt_modified")
    })
    List<User> selectAll();

    @Select("select * from user where login_name=#{loginName}")
    @Results({
            @Result(property = "loginName",column = "login_name"),
            @Result(property = "gmtCreate",column = "gmt_create"),
            @Result(property = "gmtModified",column = "gmt_modified")
    })
    User selectByName(String loginName);

    @Insert("insert into user(login_name,password,gmt_create,gmt_modified,sex,phone) values(#{loginName},#{password},#{gmtCreate},#{gmtModified},#{sex},#{phone})")
    void insert(User user);

    @Delete("delete from user where id=#{id}")
    void delete(Long id);
}
