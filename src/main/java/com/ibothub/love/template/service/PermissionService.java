package com.ibothub.love.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ibothub.love.template.model.dto.RolePermDTO;
import com.ibothub.love.template.model.entity.Permission;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 16:32
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> findByUsername(String ofUid);

    List<RolePermDTO> selectByRoleIds(List<Integer> roleIdList);
}
