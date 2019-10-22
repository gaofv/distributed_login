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
