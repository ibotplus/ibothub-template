package com.ibothub.love.template.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ibothub.love.template.model.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 16:33
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findByUsername(@Param("username") String username);

    List<Role> findByUserId(@Param("userId") Integer userId);
}
