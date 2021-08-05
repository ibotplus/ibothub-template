package com.ibothub.love.template.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/30 9:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserRoleDTO extends BaseDTO {

    private String username;

    @TableField(value = "username_cn")
    private String usernameCN;

    private String password;

    private String jobNumber;

    private Integer sex;

    private String email;

    private String phone;

    private LocalDate birthday;

    private String idCard;

    private String remark;

    private Integer roleId;

}
