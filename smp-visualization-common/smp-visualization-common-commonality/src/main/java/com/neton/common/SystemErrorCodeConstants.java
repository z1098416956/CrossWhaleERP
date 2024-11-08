package com.neton.common;

/**
 * @author TheSunshine
 * @date 2024-10-29 11:39:20
 */
public interface SystemErrorCodeConstants {

    // ========== OAUTH2 模块 ==========
    ErrorCode OAUTH2_UNKNOWN = new ErrorCode(1001001000, "未知错误"); // 预留
    // 预留 1001001001 ~ 1001001099 错误码，方便前端
    ErrorCode OAUTH2_ACCESS_TOKEN_NOT_FOUND = new ErrorCode(1001001001, "访问令牌不存在");
    ErrorCode OAUTH2_ACCESS_TOKEN_TOKEN_EXPIRED = new ErrorCode(1001001002, "访问令牌已过期");
    ErrorCode OAUTH2_ACCESS_TOKEN_INVALID = new ErrorCode(1001001003, "访问令牌已失效");
    ErrorCode OAUTH2_REFRESH_TOKEN_NOT_FOUND = new ErrorCode(1001001005, "刷新令牌不存在");
    ErrorCode OAUTH2_REFRESH_TOKEN_EXPIRED = new ErrorCode(1001001006, "访问令牌已过期");
    ErrorCode OAUTH2_REFRESH_TOKEN_INVALID = new ErrorCode(1001001007, "刷新令牌已失效");
    ErrorCode OAUTH2_TOKEN_SMS_ERROR = new ErrorCode(1001001008,"验证码不匹配");
    ErrorCode OAUTH2_TOKEN_PWD_ERROR = new ErrorCode(1001001009,"密码不匹配");
    ErrorCode OAUTH2_TOKEN_ACCOUNT_ISNULL = new ErrorCode(1001001010,"账户或密码不能为空");
    ErrorCode OAUTH2_ACCESS_CLIENT_NOT_FOUND = new ErrorCode(1001001011,"client不存在");
    ErrorCode OAUTH2_REFRESH_TOKEN_IS_NULL = new ErrorCode(1001001012,"刷新令牌不能为空");
    ErrorCode OAUTH2_ACCOUNT_IS_ENABLE = new ErrorCode(1001001013,"账号已被禁用");
    ErrorCode OAUTH2_TOKEN_ACCOUNT_ERROR = new ErrorCode(1001001014,"账户或密码不匹配");
    //================系统模块===================1001002001
    ErrorCode SYSTEM_ACCOUNT_NO_ERR = new ErrorCode(1001002001,"账号不能为空");
    ErrorCode SYSTEM_USERNAME_ERR = new ErrorCode(1001002002,"用户名称不能为空");
    ErrorCode SYSTEM_ACCOUNT_NO_REPEAT = new ErrorCode(1001002003,"账号重复");
    ErrorCode SYSTEM_USERNAME_REPEAT = new ErrorCode(1001002004,"用户名称重复");
    ErrorCode SYSTEM_USER_ID_IS_NULL = new ErrorCode(1001002005,"ID不能为空");
    ErrorCode SYSTEM_USER_ID_IS_ERR = new ErrorCode(1001002006,"用户不存在");

}
