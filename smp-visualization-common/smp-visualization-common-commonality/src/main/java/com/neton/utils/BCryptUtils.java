package com.neton.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtils {
    /**
     * 通过BCryptPasswordEncoder加密密码
     * @param pwd
     * @return
     */
    public static String getPWDStr(String pwd){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        return bCryptPasswordEncoder.encode(pwd);
    }

    /**
     * 校验密码
     * @param rawPassword 加密前的密码
     * @param encodedPassword 数据库中已经加密过后的密码
     * @return
     */
    public static boolean matchesPassword(String rawPassword,String encodedPassword){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        return bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
    }
}
