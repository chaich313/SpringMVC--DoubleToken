package com.itranlin.basic.common.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author itranlin
 */
@NoArgsConstructor
@AllArgsConstructor
public enum StatusEnum {

    /**
     * 操作成功
     */
    OK("1", "操作成功"),
    /**
     * 登录成功
     */
    SIGN_IN_OK("2", "登录成功"),
    /**
     * 注销登录成功
     */
    LOGOUT_OK("3", "注销登录成功"),
    /**
     * 账号或密码错误
     */
    SIGN_IN_INPUT_FAIL("-4", "账号或密码错误"),
    /**
     * 登录失败
     */
    SIGN_IN_FAIL("-3", "登录失败"),
    /**
     * 操作失败
     */
    FAIL("-1", "操作失败"),
    /**
     * 注销登录失败
     */
    LOGOUT_FAIL("-2", "注销登录失败"),
    /**
     * 账户和密码均不能为空
     */
    SING_IN_INPUT_EMPTY("-5", "账户和密码均不能为空"),
    /**
     * 用户未登录或身份异常
     */
    NOT_SING_IN("-6", "用户未登录或身份异常"),
    /**
     * 已有其他用户登陆，请重新登陆！！！
     */
    SING_TOO_MANY("-7", "已有其他用户登陆，请重新登陆！！！"),
    /**
     * 无权限登录，请联系管理员！
     */
    AUTHORITY_NO("-8", "无权限登录，请联系管理员！"),
    /**
     * 登陆超时
     */
    AUTHORITY_TIMEOUT("-9", "登陆超时");

    public String code;

    public String msg;

}
