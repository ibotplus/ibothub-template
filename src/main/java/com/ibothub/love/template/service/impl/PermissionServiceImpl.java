package com.ibothub.love.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ibothub.love.template.dao.PermissionMapper;
import com.ibothub.love.template.model.dto.RolePermDTO;
import com.ibothub.love.template.model.entity.Permission;
import com.ibothub.love.template.service.PermissionService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/16 11:40
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> findByUsername(String ofUid) {
        return getBaseMapper().findByUsername(ofUid);
    }

    @Override
    public List<RolePermDTO> selectByRoleIds(List<Integer> roleIdList) {
        return getBaseMapper().selectByRoleIds(roleIdList);
    }

}
