package com.ibothub.love.template.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/15 17:34
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "auth_dept_user")
public class DeptUser {

    @TableId(type= IdType.AUTO)
    private Integer id;

    private Integer deptId;

    private Integer userId;


}
