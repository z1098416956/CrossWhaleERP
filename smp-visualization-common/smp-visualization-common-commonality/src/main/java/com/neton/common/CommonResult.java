package com.neton.common;

import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author TheSunshine
 * @date 2024-10-29 11:39:48
 */
public class CommonResult <T> implements Serializable {

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 错误提示，用户可阅读，
     */
    private String message;
    /**
     * 错误明细，内部调试错误
     */
    private String detailMessage;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public CommonResult<T> setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }



    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.code();
        result.data = data;
        result.message = "操作成功";
        return result;
    }

    public static <T> CommonResult<T> success(T data,String message) {
        CommonResult<T> result = new CommonResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.code();
        result.data = data;
        result.message = message;
        return result;
    }

    public static <T> CommonResult<T> success() {
        CommonResult<T> result = new CommonResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.code();
        result.message = "操作成功";
        return result;
    }

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     * <p>
     * 例如 A 方法返回的 CommonResult 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T>    返回的泛型
     * @return 转换后 CommonResult 对象
     */
    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMessage(), result.detailMessage);
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        return error(code, message, null);
    }

    public static <T> CommonResult<T> error(ErrorCode errorCode) {

        return error(errorCode.code(), errorCode.message());
    }

    public static <T> CommonResult<T> validatedError(String message) {

        return error(GlobalErrorCodeConstants.PARAMS_VALIDATED_ERROR.code(), message);
    }


    public static <T> CommonResult<T> error(Integer code, String message, String detailMessage) {
        Assert.isTrue(!GlobalErrorCodeConstants.SUCCESS.code().equals(code), "code 必须是错误的！");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.message = message;
        result.detailMessage = detailMessage;
        return result;
    }

    public static <T> CommonResult<T> error(Integer code, String message,T t) {
        Assert.isTrue(!GlobalErrorCodeConstants.SUCCESS.code().equals(code), "code 必须是错误的！");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.data = t;
        result.message = message;
        return result;
    }



    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", detailMessage='" + detailMessage + '\'' +
                '}';
    }
}
