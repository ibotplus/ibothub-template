package com.ibothub.love.template.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/15 17:34
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Dept对象")
@TableName(value = "auth_dept")
public class Dept extends BaseEntity {

    private String name;

    private String parentId;

    @TableField(value = "key_")
    private String key;

    private String remark;

    @TableField(value = "path_")
    private String path;

    private Integer isCompany;

}
