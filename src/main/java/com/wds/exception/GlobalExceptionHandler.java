package com.wds.exception;

import com.wds.common.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-22 16:39
 *
 * controller 全局异常处理器
 * log 日志记录
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public JsonResult repeatKey(SQLIntegrityConstraintViolationException e){
        String message = e.getMessage();

        log.info("执行出现异常: {}",e.getMessage());

        if (message.contains("Duplicate entry")) {
            String[] s = message.split(" ");
            return JsonResult.error(s[2] + " 已存在");
        }else {
            return JsonResult.error("操作失败，请检查输入");
        }
    }

    @ExceptionHandler(CustomException.class)
    public JsonResult customExceptionHandler(CustomException e){
        log.info("执行出现异常: {}",e.getMessage());
        return JsonResult.error(e.getMessage());
    }
}
