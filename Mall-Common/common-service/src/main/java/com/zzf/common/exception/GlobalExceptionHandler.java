package com.zzf.common.exception;

import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zzf
 * @date 2024-01-05
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(){
        return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
    }

    //自定义异常处理
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result erro(CustomException e){
        return Result.build(null, e.getResultCodeEnum());
    }
}
