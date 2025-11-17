package com.cross.whale.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

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
     * Feign业务异常
     */
    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult<Object> handleFeignException(FeignException e) {
        logger.warn("Feign调用异常: {}", e.getMessage());

        try {
            // 尝试从异常内容中提取原始服务的错误响应
            String content = e.contentUTF8();
            if (content != null && !content.isEmpty()) {
                // 使用Jackson解析JSON响应
             //   JsonNode jsonNode = new ObjectMapper().readValue(content, JsonNode.class);
                JsonNode jsonNode = objectMapper.readTree(content);

                // 提取错误码
                JsonNode codeNode = jsonNode.get("code");
                if (codeNode != null && codeNode.isInt()) {
                    int code = codeNode.asInt();

                    // 提取错误信息
                    JsonNode messageNode = jsonNode.get("message");
                    if (messageNode != null) {
                        String message = messageNode.asText();
                        return CommonResult.error(code, message);
                    }

                    // 如果没有message字段，返回通用错误信息
                    return CommonResult.error(code, "服务调用异常");
                }
            }
        } catch (Exception parseException) {
            logger.error("解析Feign异常内容失败: ", parseException);
        }

        // 如果解析失败，返回通用错误信息
        return CommonResult.error(500, "服务调用异常");
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
