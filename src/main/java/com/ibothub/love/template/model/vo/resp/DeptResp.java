package com.ibothub.love.template.model.vo.resp;

import com.ibothub.love.template.model.vo.BaseVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/15 17:47
 */
@Schema
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DeptResp extends BaseVO {

    private String name;

    private String parentId;

    private String key;

    private String remark;

    private String path;

}
