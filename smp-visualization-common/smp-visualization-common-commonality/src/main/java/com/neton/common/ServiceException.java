package com.neton.common;

public class ServiceException extends RuntimeException {
    private Integer code;
    private String message;


    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public ServiceException(ErrorCode errorCode) {
        this.code = errorCode.code();
        this.message = errorCode.message();
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    // getter 和 setter
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
