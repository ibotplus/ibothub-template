package com.ibothub.love.template.model.vo.req;

import com.ibothub.love.template.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/16 10:16
 */
@ApiModel
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PermissionReq extends BaseVO {

    private String name;

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
    private String uri;

    private String iconFont;

    private String summary;

    /**
     * 一级菜单 CATEGORY / 二级或三级..菜单 MODULE / 按钮 BUTTON
     */
    private String type;

}
