package com.ibothub.love.template.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author yogurt_lei
 * @since 2019-06-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "User对象")
@TableName(value = "auth_user")
public class User extends BaseEntity {

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
