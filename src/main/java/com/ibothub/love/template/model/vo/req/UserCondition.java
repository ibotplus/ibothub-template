package com.ibothub.love.template.model.vo.req;

import com.ibothub.love.template.util.pageable.PageInfoRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/29 10:53
 */
@Schema
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCondition extends PageInfoRequest {

    private Integer deptId;

    private String username;

    private String usernameCN;
}
