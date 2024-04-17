package com.ibothub.love.template.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * base view object used for convert frontend request pojo
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2019-04-12 17:25
 */
@Data
@Validated
@Schema
public class BaseVO implements Serializable {

    @NotEmpty(groups = Update.class)
    @Schema(description = "ID")
    private String id;

    public interface Create {
    }

    public interface Update {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
