#### pom.xml

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.0.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.gaofv</groupId>
    <artifactId>zhuowang-exam</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>zhuowang-exam</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!--springdata操作redis-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <!--<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.3</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <!--使用热部署出现中文乱码解决方案-->
                <configuration>
                    <fork>true</fork>
                    <!--增加jvm参数-->
                    <jvmArguments>-Dfile.encoding=UTF-8</jvmArguments>
                    <!--指定入口类-->
                    <mainClass>com.gaofv.ZhuowangExamApplication</mainClass>
                    <!--打包方式指定jar-->
                    <layout>JAR</layout>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>

~~~

#### application.yml

~~~yml
server:
  port: 8888
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    url: jdbc:mysql://localhost:3306/zhuowang_exam
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
  redis:
    port: 6379
    host: 192.168.145.3
    database: 0
mybatis:
  type-aliases-package: com.gaofv.entity
~~~

#### logback.xml

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>
  <!--  &lt;!&ndash;定义项目中日志输出位置&ndash;&gt;
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        &lt;!&ndash;定义项目的日志输出格式&ndash;&gt;
        &lt;!&ndash;定义项目的日志输出格式&ndash;&gt;
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern> [%p] %d{yyyy-MM-dd HH:mm:ss} %m %n</pattern>
        </layout>
    </appender>

    &lt;!&ndash;项目中跟日志控制&ndash;&gt;
    <root level="INFO">
        <appender-ref ref="stdout"/>
    </root>
    &lt;!&ndash;项目中指定包日志控制&ndash;&gt;
    <logger name="com.baizhi.dao" level="DEBUG"/>-->


    <!--定义项目中的日志输出位置-->
    <appender name = "stdout" class="ch.qos.logback.core.ConsoleAppender">
        <!--定义项目的输出日志格式-->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <!--[%p] :展示日志级别-->
            <!--[%d] :展示日志时间-->
            <!--[%m] :展示当前主线程运行输出-->
            <!--[%n] :回车换行-->
            <pattern> [%p] %d{yyyy-MM-dd HH:mm:ss} %m %n </pattern>
        </layout>
    </appender>

    <!--项目中的跟日志控制-->
    <root level="INFO">
        <appender-ref ref="stdout"/>
    </root>
    <!--项目中指定包日志控制-->
    <logger name="com.baizhi.dao" level="DEBUG"/>
</configuration>
~~~

#### com.gaofv.entity

~~~java
package com.gaofv.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    @Length(min = 4,max = 20,message = "登陆名最短 4 个字符，最长 20 个字符")
    private String loginName;
    @Length(min = 6,max = 20,message = "密码最短 6 个字符，最长 20 个字符")
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    @Pattern(regexp = "^(0|1){1}$",message = "只能是 1（男）或者 0（女）")
    private String sex;
    @Pattern(regexp = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\\\d{8}$)|(^1705\\\\d{7}$)",message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    private String phone;
}


package com.gaofv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: GF
 * @Date: 2019/10/20
 * @Description: com.gaofv.entity
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Boolean success;
    private String message;
}

~~~

#### com.gaofv.dao

~~~java
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

~~~

#### com.gaofv.service

~~~java
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

~~~

#### com.gaofv.service.impl

~~~java
package com.gaofv.service.impl;

import com.gaofv.entity.Result;
import com.gaofv.entity.User;
import com.gaofv.mapper.UserMapper;
import com.gaofv.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
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

~~~

#### com.gaofv.controller

~~~java
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

~~~

#### com.gaofv.config

SpringUtil

~~~java
package com.gaofv.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {
 
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(this.applicationContext==null){
            this.applicationContext = applicationContext;
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
 
    //通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
 
    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
 
    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
~~~

LoginInterceptor

~~~java
package com.gaofv;

import com.gaofv.config.SpringUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: GF
 * @Date: 2019/10/21
 * @Description: com.gaofv
 * @version: 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //遍历redis中user的值
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean("stringRedisTemplate", StringRedisTemplate.class);
        String user = stringRedisTemplate.opsForValue().get("user");
        System.out.println("prehandle:::" +user);
        if(StringUtils.isEmpty(user)){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
~~~

LoginInterceptorConfig

~~~java
package com.gaofv.config;

import com.gaofv.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Auther: GF
 * @Date: 2019/10/21
 * @Description: com.gaofv.config
 * @version: 1.0
 */
@Configuration
public class LoginInterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/regist","/user/login");
    }
}

~~~

