package com.ibothub.love.template.util.pageable;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import java.io.Serializable;

/**
 *
 * page request
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2019-04-10 18:23
 */

@Data
@Schema(description = "分页请求")
@Accessors(chain = true)
@Validated
public class PageInfoRequest implements Serializable {

    @Min(1)
    @Schema(description = "分页参数: 起始页为1", example = "1")
    private int pageNum;

    @Min(10)
    @Schema(description = "分页参数: 页大小", example = "10")
    private int pageSize;

    // TODO by Yogurt_lei on 2019-04-14 13:42 : OrderBy,Where Fields

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
