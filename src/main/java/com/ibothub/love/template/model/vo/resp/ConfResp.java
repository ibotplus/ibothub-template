package com.ibothub.love.template.model.vo.resp;

import com.ibothub.love.template.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/15 17:46
 */
@ApiModel
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ConfResp extends BaseVO {

    private String code;

    private String value;

    private String defaultValue;

    private String name;

    private String remark;

    private Integer type;

    private String raw;

}
