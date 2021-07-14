package com.ibothub.love.template.adapter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibothub.love.template.model.BeanConverter;
import com.ibothub.love.template.model.entity.Role;
import com.ibothub.love.template.model.vo.req.RoleReq;
import com.ibothub.love.template.model.vo.resp.RoleResp;
import com.ibothub.love.template.service.RoleService;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 16:18
 */
@Component
public class RoleAdapter {

    @Resource
    RoleService roleService;

    @Resource
    BeanConverter beanConverter;

    public void saveOrUpdate(RoleReq vo) {
        roleService.saveOrUpdate(beanConverter.forward(vo));
    }

    public RoleResp getById(Integer id) {
        return beanConverter.backward(roleService.getById(id));
    }

    public void deleteById(Integer id) {
        roleService.removeById(id);
    }

    public IPage<RoleResp> queryByPage(PageInfoRequest pageInfoRequest) {
        IPage<Role> pageParam = new Page<>(pageInfoRequest.getPageNum(), pageInfoRequest.getPageSize());
        IPage<Role> pageResult = roleService.page(pageParam);
        return pageResult.convert(beanConverter::backward);
    }
}