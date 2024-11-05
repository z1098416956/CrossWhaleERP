package com.neton.utils;

import com.neton.constants.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SecurityUtils {

    /**
     * 获取用户名
     */
    public static String getUsername() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object attribute = request.getAttribute(SecurityConstants.DETAILS_USERNAME);
        if (attribute != null) {
            return request.getAttribute(SecurityConstants.DETAILS_USERNAME).toString();
        } else {

            return "";
        }
    }

    /**
     * 设置用户名
     */
    public static void setUsername(String name) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute(SecurityConstants.DETAILS_USERNAME, name);
    }

    /**
     * 获取真实姓名
     */
    public static String getAccount() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object obj = request.getAttribute(SecurityConstants.DETAILS_ACCOUNT);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 设置真实姓名
     */
    public static void setAccount(String account) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute(SecurityConstants.DETAILS_ACCOUNT, account);
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object userId = request.getAttribute(SecurityConstants.DETAILS_USER_ID);
        return null == userId ? -1L : Long.parseLong(userId.toString());
    }

    /**
     * 设置用户ID
     */
    public static void setUserId(Long userId) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute(SecurityConstants.DETAILS_USER_ID, userId);
    }

}
