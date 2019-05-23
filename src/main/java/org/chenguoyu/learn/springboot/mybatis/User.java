package org.chenguoyu.learn.springboot.mybatis;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户类value",description = "用户类description")
public class User {
    /**
     * id
     */
    @ApiModelProperty(name = "id",value = "主键ID")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(name = "username",value = "用户名",required = true)
    private String username;

    /**
     * 性别
     */
    @ApiModelProperty(name = "sex",value = "性别",allowableValues = "0,1",required = true)
    private Integer sex;
    /**
     * 年龄
     */
    @ApiModelProperty(name = "age",value = "年龄",allowableValues = "range[1,100]",required = true)
    private Integer age;
    /**
     * 生日
     */
    @ApiModelProperty(name = "birthday",value = "生日")
    private LocalDate birthday;
}