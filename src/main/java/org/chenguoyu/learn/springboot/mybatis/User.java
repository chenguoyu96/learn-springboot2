package org.chenguoyu.learn.springboot.mybatis;


import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 生日
     */
    private LocalDate birthday;
}