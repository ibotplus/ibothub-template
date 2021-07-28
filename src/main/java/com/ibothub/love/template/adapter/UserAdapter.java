package com.ibothub.love.template.adapter;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.ibothub.love.template.model.BeanConverter;
import com.ibothub.love.template.model.entity.Role;
import com.ibothub.love.template.model.entity.User;
import com.ibothub.love.template.model.entity.UserRole;
import com.ibothub.love.template.model.vo.req.UserReq;
import com.ibothub.love.template.model.vo.resp.UserResp;
import com.ibothub.love.template.service.RoleService;
import com.ibothub.love.template.service.UserRoleService;
import com.ibothub.love.template.service.UserService;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version 1.0
 * @date 2021/7/13 21:18
 */
@Component
public class UserAdapter {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleService roleService;


    @Resource
    BeanConverter beanConverter;

    public void saveOrUpdate(UserReq vo){
        User user = beanConverter.forward(vo);
        userService.saveOrUpdate(user);

        if (ArrayUtils.isNotEmpty(vo.getRoleList())) {
            // 删除该用户关联的角色
            userRoleService.remove(Wrappers.lambdaQuery(UserRole.class).eq(UserRole::getUserId, user.getId()));
            // 添加关联关系
            List<UserRole> userRoleList = Lists.newArrayList();
            Arrays.stream(vo.getRoleList())
                    .forEach(roleId ->
                        userRoleList.add(
                                UserRole.builder()
                                    .userId(user.getId())
                                    .roleId(Integer.valueOf(roleId))
                                    .build()
                        )
                    );
            userRoleService.saveBatch(userRoleList);
        }
    }

    public void deleteById(Integer id){
        // 删除该用户关联的角色
        userRoleService.remove(Wrappers.lambdaQuery(UserRole.class).eq(UserRole::getUserId, id));
        userService.removeById(id);
    }

    public UserResp getById(Integer id){
        List<Role> roleList = roleService.findByUserId(id);
        UserResp userResp = beanConverter.backward(userService.getById(id));
        userResp.setRoleList(beanConverter.backward(roleList));
        return userResp;
    }

    public IPage<UserResp> queryByPage(PageInfoRequest pageInfoRequest){
        IPage<User> pageParam = new Page<>(pageInfoRequest.getPageNum(), pageInfoRequest.getPageSize());
        IPage<User> pageResult = userService.page(pageParam);
        return pageResult.convert(beanConverter::backward);
    }

    public UserResp getByUsername(String username) {
        Wrapper<User> query = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username);
        User user = userService.getOne(query);
        List<Role> roleList = roleService.findByUsername(username);
        user.setRoleList(roleList);
        return beanConverter.backward(user);

    }
}
