package com.example.springbootproject.entity;

import lombok.Getter;
import lombok.Setter;

/**
 *  一些常用的字段验证的注解
 *  @NotEmpty 被注释的字符串的不能为 null 也不能为空
 *  @NotBlank 被注释的字符串非 null，并且必须包含一个非空白字符
 *  @Null 被注释的元素必须为 null
 *  @NotNull 被注释的元素必须不为 null
 *  @AssertTrue 被注释的元素必须为 true
 *  @AssertFalse 被注释的元素必须为 false
 *  @Pattern(regex=,flag=)被注释的元素必须符合指定的正则表达式
 *  @Email 被注释的元素必须是 Email 格式。
 *  @Min(value)被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 *  @Max(value)被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 *  @DecimalMin(value)被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 *  @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 *  @Size(max=, min=)被注释的元素的大小必须在指定的范围内
 *  @Digits(integer, fraction)被注释的元素必须是一个数字，其值必须在可接受的范围内
 *  @Past被注释的元素必须是一个过去的日期
 *  @Future 被注释的元素必须是一个将来的日期
 */
@Setter
@Getter
public class User {
    private int id;
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "\"User\": {" +
                    "\"id\": " + "\"" + id + "\"" + "," +
                    "\"username\": " + "\"" + username + "\"" + "," +
                    "\"password\": " + "\"" + password + "\"" +
                '}' +
                '}';
    }
}
