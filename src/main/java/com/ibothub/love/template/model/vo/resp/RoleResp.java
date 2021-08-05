package com.ibothub.love.template.model.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibothub.love.template.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 16:29
 */
@ApiModel
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RoleResp extends BaseVO {

    @NotEmpty
    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色标识")
    private String key;

    @ApiModelProperty("备注")
    private String remark;

    private List<UserResp> userList;

    private List<PermissionResp> permList;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime modifyTime;
}
