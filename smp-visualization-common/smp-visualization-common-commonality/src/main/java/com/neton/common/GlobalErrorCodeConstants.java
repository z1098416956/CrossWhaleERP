package com.neton.common;

/**
 * @author TheSunshine
 * @date 2024-10-29 11:38:44
 */
public interface GlobalErrorCodeConstants {
    ErrorCode SUCCESS = new ErrorCode(0, "成功");
    ErrorCode PARAMS_VALIDATED_ERROR = new ErrorCode(9999, "参数校验错误");

    // ============================== 客户端错误段 ==============================
    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");
    ErrorCode UNAUTHORIZED = new ErrorCode(401, "账号未登录");
    ErrorCode FORBIDDEN = new ErrorCode(403, "没有该操作权限");
    ErrorCode NOT_FOUND = new ErrorCode(404, "请求未找到");
    ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405, "请求方法不正确");
    ErrorCode REPEAT_REQUEST = new ErrorCode(430, "请求重复提交");

    // ============================== 服务端错误段 ==============================
    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "系统异常");
    ErrorCode UNKNOWN = new ErrorCode(999, "未知错误");

    static boolean isMatch(Integer code) {
        return code != null && code >= SUCCESS.code() && code <= UNKNOWN.code();
    }
}
