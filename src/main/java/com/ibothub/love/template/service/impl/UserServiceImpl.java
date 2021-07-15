package com.ibothub.love.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.ibothub.love.template.dao.UserMapper;
import com.ibothub.love.template.model.entity.Role;
import com.ibothub.love.template.model.entity.User;
import com.ibothub.love.template.service.RoleService;
import com.ibothub.love.template.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yogurt_lei
 * @since 2019-06-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    RoleService roleService;

    @Override
    public UserDetails findByUsername(String username) {
        Wrapper<User> query = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username);
        User user = this.getOne(query);
        if (user==null) {
            return null;
        }

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(roleService.getGrantedAuthorities(username))
                .build();
    }
}
