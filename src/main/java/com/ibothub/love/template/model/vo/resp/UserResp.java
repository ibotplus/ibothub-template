package com.ibothub.love.template.model.vo.resp;

import com.ibothub.love.template.model.vo.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(description = "UserResp")
public class UserResp extends BaseVO {

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createTime;

    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime modifyTime;

    @Schema(description = "用户名")
    private String username;

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

    @Schema(description = "备注")
    private String remark;

    private List<RoleResp> roleList;

    private List<DeptResp> deptList;
}
