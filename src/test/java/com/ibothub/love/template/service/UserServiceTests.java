package com.ibothub.love.template.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ibothub.love.template.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
}
