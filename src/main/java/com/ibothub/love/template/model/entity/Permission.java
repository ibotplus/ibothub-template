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
 * @date 2021/7/15 18:09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Permission对象")
@TableName(value = "auth_permission")
public class Permission extends BaseEntity {

    private String name;

    /**
     * 权限标识
     */
    @TableField(value = "key_")
    private String key;

    /**
     * 上级id
     */
    private String parentId;

    @TableField(value = "order_")
    private Integer order;

    /**
     * 访问路径
     */
    @TableField(value = "uri_")
    private String uri;

    private String iconFont;

    private String summary;

    /**
     * 一级菜单 CATEGORY / 二级或三级..菜单 MODULE / 按钮 BUTTON
     */
    @TableField(value = "type_")
    private String type;

}
