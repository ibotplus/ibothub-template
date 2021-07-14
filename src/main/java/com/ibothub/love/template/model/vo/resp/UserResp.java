package com.ibothub.love.template.model.vo.resp;

import com.ibothub.love.template.model.vo.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel(value = "UserResp", description = "")
public class UserResp extends BaseVO {

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime modifyTime;

    @ApiModelProperty("用户名")
    private String username;

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
}
