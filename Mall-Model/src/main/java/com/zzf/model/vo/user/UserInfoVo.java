package com.zzf.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zzf
 * @date 2024-01-18
 */
@Data
@Schema(description = "用户类")
public class UserInfoVo {

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "头像")
    private String avatar;

}
