package com.ibothub.love.template.adapter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.ibothub.love.template.model.BeanConverter;
import com.ibothub.love.template.model.dto.RolePermDTO;
import com.ibothub.love.template.model.dto.UserRoleDTO;
import com.ibothub.love.template.model.entity.Role;
import com.ibothub.love.template.model.entity.RolePerm;
import com.ibothub.love.template.model.vo.req.RoleReq;
import com.ibothub.love.template.model.vo.resp.RoleResp;
import com.ibothub.love.template.service.PermissionService;
import com.ibothub.love.template.service.RolePermService;
import com.ibothub.love.template.service.RoleService;
import com.ibothub.love.template.service.UserService;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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

    @Resource
    UserService userService;

    @Resource
    PermissionService permissionService;

    @Resource
    RolePermService rolePermService;

    public void saveOrUpdate(RoleReq vo) {
        Role role = beanConverter.forward(vo);
        roleService.saveOrUpdate(role);

        if (vo.getPermIdList()!=null && vo.getPermIdList().length>0){

            rolePermService.remove(Wrappers.<RolePerm>lambdaQuery().eq(RolePerm::getRoleId, role.getId()));

            List<RolePerm> rolePermList = Lists.newArrayList();
            Arrays.stream(vo.getPermIdList())
                    .forEach(permId -> {
                        rolePermList.add(RolePerm.builder()
                                .roleId(role.getId())
                                .permId(Integer.valueOf(permId))
                                .build());
                    });
            rolePermService.saveBatch(rolePermList);
        }
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

        List<Integer> roleIdList = pageResult.getRecords()
                .stream()
                .map(Role::getId)
                .collect(Collectors.toList());

        Map<Integer, List<UserRoleDTO>> userMap = userService.selectByRoleIds(roleIdList)
                .stream()
                .collect(Collectors.groupingBy(UserRoleDTO::getRoleId));

        Map<Integer, List<RolePermDTO>> permMap = permissionService.selectByRoleIds(roleIdList)
                .stream()
                .collect(Collectors.groupingBy(RolePermDTO::getRoleId));


        IPage<RoleResp> roleRespPage = pageResult.convert(beanConverter::backward);

        // 填充角色下的用户到角色 roleResp
        roleRespPage
                .getRecords()
                .forEach(role -> {
                    List<UserRoleDTO> userRoleList = userMap.get(Integer.valueOf(role.getId()));
                    if (userRoleList!=null){
                        role.setUserList(beanConverter.backwardUserDTO(userRoleList));
                    }

                    List<RolePermDTO> rolePermList = permMap.get(Integer.valueOf(role.getId()));
                    if (rolePermList!=null){
                        role.setPermList(beanConverter.backwardPermDTO(rolePermList));
                    }
                });

        return roleRespPage;
    }

    public List<RoleResp> queryList(RoleReq vo) {
        if (StringUtils.isNotBlank(vo.getName())){
            return beanConverter.backward(roleService.lambdaQuery()
                    .like(Role::getName, vo.getName())
                    .select(Role::getName, Role::getId)
                    .list());
        }else{
            return beanConverter.backward(roleService.lambdaQuery()
                    .select(Role::getName, Role::getId)
                    .list());
        }
    }
}
