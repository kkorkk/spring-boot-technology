package com.demo.springbootshirodemo.common;

/**
 * @Author：kkorkk.
 * @Date：2018/10/22 17:39
 * @Description：关键常量
 */
public interface CoreConstants {

    /**
     * 密码默认的盐
     * */
    String PASSWORD_SALT = "123";
    /**
     * 加密种类
     * */
    String PASSWORD_ALGORITHM_NAME = "MD5";
    /**
     * 加密次数
     * */
    int PASSWORD_HASH_ITERATIONS = 2;
    /**
     * 用户状态：可用
     * */
    Byte USER_STATE_NORMAL = 1;
    /**
     * 用户状态：未激活（冻结）
     * */
    Byte USER_STATE_FREEZE = 2;
    /**
     * 用户状态：已删除
     * */
    Byte USER_STATE_DELETED = 3;


}
