package com.ibothub.love.template.adapter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibothub.love.template.model.BeanConverter;
import com.ibothub.love.template.model.entity.User;
import com.ibothub.love.template.model.vo.req.UserReq;
import com.ibothub.love.template.model.vo.resp.UserResp;
import com.ibothub.love.template.service.UserService;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    BeanConverter beanConverter;

    public void saveOrUpdate(UserReq vo){
        userService.saveOrUpdate(beanConverter.forward(vo));
    }

    public void deleteById(Integer id){
        userService.removeById(id);
    }

    public UserResp getById(Integer id){
        return beanConverter.backward(userService.getById(id));
    }

    public IPage<UserResp> queryByPage(PageInfoRequest pageInfoRequest){
        IPage<User> pageParam = new Page<>(pageInfoRequest.getPageNum(), pageInfoRequest.getPageSize());
        IPage<User> pageResult = userService.page(pageParam);
        return pageResult.convert(beanConverter::backward);
    }
}
