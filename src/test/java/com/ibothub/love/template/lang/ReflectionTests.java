package com.ibothub.love.template.lang;

import com.ibothub.love.template.model.entity.BaseEntity;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/23 16:55
 */
public class ReflectionTests {

    @Test
    public void test(){
        ReflectionUtils.doWithFields(BaseEntity.class, (fc)->{
            System.out.println(fc.getName());
        });
    }
}
