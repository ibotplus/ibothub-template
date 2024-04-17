package com.ibothub.love.template.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/8/6 15:56
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Schema(description = "系统配置-附件")
@TableName(value = "sys_conf_extra")
public class ConfExtra {

    @TableId(type= IdType.AUTO)
    protected Integer id;
    private Integer confId;
    private byte[] content;
}
