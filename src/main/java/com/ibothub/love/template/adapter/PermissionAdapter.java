package com.ibothub.love.template.adapter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.ibothub.love.template.model.BeanConverter;
import com.ibothub.love.template.model.entity.Permission;
import com.ibothub.love.template.model.vo.BaseVO;
import com.ibothub.love.template.model.vo.req.PermissionReq;
import com.ibothub.love.template.model.vo.resp.PermissionResp;
import com.ibothub.love.template.service.PermissionService;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<PermissionResp> getByUsername(String ofUid) {
        List<PermissionResp> permList = beanConverter.backward(permissionService.findByUsername(ofUid));

///        List<PermissionResp> permTreeList = permList.stream()
//                .filter(permissionResp -> permissionResp.getParentId() == null)
//                .collect(Collectors.toList());
//
//        permTreeList.forEach(permissionResp -> fillChildren(permList, permissionResp));
        return permList;
    }

    private void fillChildren(List<PermissionResp> list, PermissionResp parent){
        if (parent==null || list==null) return;
        List<PermissionResp> children = list.stream().filter(permissionResp -> parent.getId().equals(permissionResp.getParentId()))
                .peek(permissionResp -> fillChildren(list, permissionResp))
                .collect(Collectors.toList());
        parent.setChildren(children);
    }
}
