package com.ibothub.love.template.model.vo.req;

import com.ibothub.love.template.model.vo.BaseVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 16:29
 */
@Schema
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RoleReq extends BaseVO {

    @NotEmpty(groups={Create.class, Update.class})
    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色标识")
    private String key;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "权限id")
    private String[] permIdList;
}
