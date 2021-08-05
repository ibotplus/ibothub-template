package com.ibothub.love.template.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * TODO 补充 creator 和 modifier 的数据填充
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/23 16:49
 */
@Component
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_TIME_FIELD = "createTime";
    private static final String CREATOR_FIELD = "creator";
    private static final String MODIFY_TIME_FIELD = "modifyTime";
    private static final String MODIFIER_FIELD = "modifier";

    @Override
    public void insertFill(MetaObject metaObject) {
        // 更新 创建时间，创建人
        if (metaObject.hasGetter(CREATE_TIME_FIELD)) {
            this.strictInsertFill(metaObject, CREATE_TIME_FIELD, LocalDateTime.class, LocalDateTime.now());
        }
        this.updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新 修改时间，修改人
        if (metaObject.hasGetter(MODIFY_TIME_FIELD)) {
            this.strictInsertFill(metaObject, MODIFY_TIME_FIELD, LocalDateTime.class, LocalDateTime.now());
        }
    }
}
