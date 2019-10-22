package com.gaofv;

import com.gaofv.entity.User;
import com.gaofv.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@SpringBootTest
class ZhuowangExamApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserMapper userMapper;
    @Test
    public void test4(){
        userMapper.insert(new User(null,"gaofei","123",new Date(),new Date(),"1","15515646965"));
    }

    @Test
    public void test5(){
        System.out.println(userMapper.selectByName("gaofei"));
    }

    @Test
    public void test3(){
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test6(){
        /*String user = stringRedisTemplate.opsForValue().get("user");
        System.out.println(user);*/
        //System.out.println(Pattern.matches("^(0|1){1}$", "1"));
       //System.out.println(Pattern.matches("^(?:\\+?86)?1(?:3(?:4[^9\\D]|[5-9]\\d)|5[^3-6\\D]\\d|8[23478]\\d|(?:78|98)\\d)\\d{7}$","15515646965"));

        //Long size = redisTemplate.opsForList().("user");
        //System.out.println(size);
        List<String> users = redisTemplate.opsForList().range("user", 0, 100);
        for (String user : users) {
            System.out.println(user);
        }

        Set keys = redisTemplate.keys("*");
        for (Object key : keys) {
            System.out.println(key);
        }
    }
}
