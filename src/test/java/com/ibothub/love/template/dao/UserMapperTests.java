package com.ibothub.love.template.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.ibothub.love.template.model.entity.User;
import com.ibothub.love.template.model.vo.req.UserCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/29 11:27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Resource
    UserMapper userMapper;

    @Test
    public void testSelectPage(){
        IPage<User> pageParam = new Page<>(0, 10);
        UserCondition userCondition = new UserCondition();
        userCondition.setDeptId(99999);
        IPage<User> userPage = userMapper.selectPage(pageParam, userCondition);
        System.out.println(userPage);
    }

    @Test
    public void testSelectByRoleIds(){
        userMapper.selectByRoleIds(Lists.newArrayList(99999, 100003)).stream()
        .forEach(System.out::println);
    }
}
