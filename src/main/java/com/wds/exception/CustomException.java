package com.wds.exception;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-22 16:46
 *
 * 自定义异常   方便被异常处理器处理
 */
public class CustomException extends RuntimeException{
    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }
}
