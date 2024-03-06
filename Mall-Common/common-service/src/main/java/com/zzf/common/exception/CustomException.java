package com.zzf.common.exception;

import com.zzf.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author zzf
 * @date 2024-01-05
 */
@Data
public class CustomException extends RuntimeException {
    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public CustomException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.resultCodeEnum = resultCodeEnum;
    }
}
