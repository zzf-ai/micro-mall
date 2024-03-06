package com.zzf.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zzf
 * @date 2024-01-05
 */
@Data
@Schema(description = "登录成功响应结果实体类")
public class LoginVo {
    @Schema(description = "令牌")
    private String token ;

    @Schema(description = "刷新令牌,可以为空")
    private String refresh_token ;
}

