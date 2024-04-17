package com.ibothub.love.template.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * base entity used for wrap datasource pojo
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2019-03-27 9:04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "base entity")
@Slf4j
public class BaseEntity implements Serializable {

    @TableId(type= IdType.AUTO)
    protected Integer id;

    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    @Schema(description = "修改时间")
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime modifyTime;

    @Schema(description = "创建人")
    protected String creator;

    @Schema(description = "修改人")
    protected String modifier;

    /**
     * Logic to delete is 1, else is 0
     */
    @Schema(description = "逻辑删除标志")
    protected Integer delFlag;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }
}
