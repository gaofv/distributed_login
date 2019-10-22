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

/**
 * @Auther: GF
 * @Date: 2019/10/20
 * @Description: com.gaofv.entity
 * @version: 1.0
 */
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
