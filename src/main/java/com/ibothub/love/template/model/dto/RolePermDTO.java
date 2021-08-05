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
public class RolePermDTO extends BaseDTO {

    /**
     * 权限英文标识，用于vue中的name
     */
    private String name;

    /**
     * 权限的中文名，用于vue中的title
     */
    private String title;

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
     * 访问路径，如 system/user
     */
    @TableField(value = "uri_")
    private String uri;

    private String iconFont;

    private String summary;


    /**
     * 访问路径，跳转路径
     */
    @TableField(value = "redirect_")
    private String redirect;

    /**
     * 前端组件 () => import('@/views/dashboard/index') or Layout
     */
    private String component;

    private Integer isMenu;

    /**
     * 一级菜单 CATEGORY / 二级或三级..菜单 MODULE / 按钮 BUTTON
     */
    @TableField(value = "type_")
    private String type;

    private Integer roleId;

}
