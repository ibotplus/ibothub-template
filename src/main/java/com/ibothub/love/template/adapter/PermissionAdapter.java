package com.ibothub.love.template.adapter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibothub.love.template.model.BeanConverter;
import com.ibothub.love.template.model.entity.Permission;
import com.ibothub.love.template.model.vo.req.PermissionReq;
import com.ibothub.love.template.model.vo.resp.PermissionResp;
import com.ibothub.love.template.service.PermissionService;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/16 11:39
 */
@Component
public class PermissionAdapter {

    @Resource
    PermissionService permissionService;

    @Resource
    BeanConverter beanConverter;

    public void saveOrUpdate(PermissionReq vo) {
        permissionService.saveOrUpdate(beanConverter.forward(vo));
    }

    public PermissionResp getById(Integer id) {
        return beanConverter.backward(permissionService.getById(id));
    }

    public void deleteById(Integer id) {
        permissionService.removeById(id);
    }

    public IPage<PermissionResp> queryByPage(PageInfoRequest pageInfoRequest) {
        IPage<Permission> pageParam = new Page<>(pageInfoRequest.getPageNum(), pageInfoRequest.getPageSize());
        IPage<Permission> pageResult = permissionService.page(pageParam);
        return pageResult.convert(beanConverter::backward);
    }

}
