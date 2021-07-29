package com.ibothub.love.template.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ibothub.love.template.model.entity.User;
import com.ibothub.love.template.model.vo.req.UserCondition;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * simple demo for entity curd
 * </p>
 *
 * @author yogurt_lei
 * @since 2019-06-19
 */
public interface UserService extends IService<User> {

    UserDetails findByUsername(String username);

    IPage<User> page(IPage<User> page, UserCondition userCondition);
}
