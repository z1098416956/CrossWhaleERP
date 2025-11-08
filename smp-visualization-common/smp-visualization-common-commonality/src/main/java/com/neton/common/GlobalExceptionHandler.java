package com.neton.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult<Object> handleServiceException(ServiceException e) {
        logger.warn("业务异常: {}", e.getMessage());
        return CommonResult.error(
                e.getCode() != null ? e.getCode() : 400,
                e.getMessage()
        );
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<Object> handleRuntimeException(RuntimeException e) {
        logger.error("运行时异常: ", e);
        return CommonResult.error(500, "系统运行时异常");
    }

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<Object> handleException(Exception e) {
        logger.error("系统异常: ", e);
        return CommonResult.error(500, "系统内部错误");
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<Object> handleNullPointerException(NullPointerException e) {
        logger.error("空指针异常: ", e);
        return CommonResult.error(500, "空指针异常");
    }
}
