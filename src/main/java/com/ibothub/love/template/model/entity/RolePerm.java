package com.ibothub.love.template.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/8/5 18:56
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "auth_role_permission")
public class RolePerm {

    @TableId(type= IdType.AUTO)
    private Integer id;

    private Integer roleId;

    @TableField(value = "permission_id")
    private Integer permId;
}
