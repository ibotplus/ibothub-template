package com.ibothub.love.template.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibothub.love.template.model.entity.User;
import com.ibothub.love.template.model.vo.req.UserCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version 1.0
 * @date 2021/7/13 22:06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Resource
    UserService userService;

    @Test
    public void testGetOne(){
        Wrapper<User> query = Wrappers.<User>lambdaQuery().eq(User::getUsername, "zhangsan");

        User user = userService.getOne(query);
        System.out.println(user.toString());
    }

    @Test
    public void testSelectPage(){
        IPage<User> pageParam = new Page<>(0, 10);
        UserCondition userCondition = new UserCondition();
        userCondition.setDeptId(99999);
        IPage<User> page = userService.page(pageParam, userCondition);
        System.out.println(page);
    }
}
