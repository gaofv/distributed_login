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
