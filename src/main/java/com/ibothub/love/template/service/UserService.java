package com.ibothub.love.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ibothub.love.template.model.entity.User;
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
}
