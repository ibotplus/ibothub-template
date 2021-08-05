package com.ibothub.love.template.model.vo.req;

import com.ibothub.love.template.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@ApiModel("userReq")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserReq extends BaseVO {

    @NotEmpty(groups = Create.class)
    @ApiModelProperty("用户名")
    private String username;

    @NotEmpty(groups = Create.class)
    @ApiModelProperty("密码(前端md5后传入)")
    private String password;

    @NotEmpty
    @ApiModelProperty("用户中文名")
    private String usernameCN;

    @ApiModelProperty("用户工号")
    private String jobNumber;

    @ApiModelProperty("用户性别")
    private Integer sex;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户手机号")
    private String phone;

    @ApiModelProperty("用户生日")
    private String birthday;

    @ApiModelProperty("身份证")
    private String idCard;

    @ApiModelProperty("关联的角色id")
    private String[] roleList;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("所属部门id")
    private String[] deptList;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
