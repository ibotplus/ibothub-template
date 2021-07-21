package com.ibothub.love.template.model.vo.resp;

import com.ibothub.love.template.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/16 10:16
 */
@ApiModel
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PermissionResp extends BaseVO {

    private String name;
    private String title;

    /**
     * 权限标识
     */
    private String key;

    /**
     * 上级id
     */
    private String parentId;

    private Integer order;

    /**
     * 访问路径
     */
    private String path;

    private String iconFont;

    private String summary;

    private String redirect;

    private String component;

    private Integer isMenu;

    /**
     * 一级菜单 CATEGORY / 二级或三级..菜单 MODULE / 按钮 BUTTON
     */
    private String type;

    private List<PermissionResp> children;

}
