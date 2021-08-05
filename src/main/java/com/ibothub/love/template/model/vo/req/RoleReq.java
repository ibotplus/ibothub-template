package com.ibothub.love.template.model.vo.req;

import com.ibothub.love.template.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 16:29
 */
@ApiModel
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RoleReq extends BaseVO {

    @NotEmpty(groups={Create.class, Update.class})
    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色标识")
    private String key;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("权限id")
    private String[] permIdList;
}
