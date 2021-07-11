package com.ibothub.love.template.model.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("base entity")
@Slf4j
public class BaseEntity implements Serializable {

    @TableId(type= IdType.AUTO)
    protected String id;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty("创建时间")
    protected LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    protected LocalDateTime modifyTime;

    @ApiModelProperty("创建人")
    protected String creator;

    @ApiModelProperty("修改人")
    protected String modifier;

    /**
     * Logic to delete is 1, else is 0
     */
    @ApiModelProperty("逻辑删除标志")
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
