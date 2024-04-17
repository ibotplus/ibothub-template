package com.ibothub.love.template.model.vo.req;

import com.ibothub.love.template.model.vo.BaseVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "userReq")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserReq extends BaseVO {

    @NotEmpty(groups = Create.class)
    @Schema(description = "用户名")
    private String username;

    @NotEmpty(groups = Create.class)
    @Schema(description = "密码(前端md5后传入)")
    private String password;

    @NotEmpty
    @Schema(description = "用户中文名")
    private String usernameCN;

    @Schema(description = "用户工号")
    private String jobNumber;

    @Schema(description = "用户性别")
    private Integer sex;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "用户手机号")
    private String phone;

    @Schema(description = "用户生日")
    private String birthday;

    @Schema(description = "身份证")
    private String idCard;

    @Schema(description = "关联的角色id")
    private String[] roleList;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "所属部门id")
    private String[] deptList;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
