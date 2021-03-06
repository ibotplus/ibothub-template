package com.ibothub.love.template.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ibothub.love.template.model.dto.RolePermDTO;
import com.ibothub.love.template.model.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/15 17:51
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findByUsername(String username);

    List<RolePermDTO> selectByRoleIds(@Param(("roleIdList")) List<Integer> roleIdList);
}
