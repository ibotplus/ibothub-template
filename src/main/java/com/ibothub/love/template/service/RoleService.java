package com.ibothub.love.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ibothub.love.template.model.entity.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 16:32
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户名称查找角色
     * @param username
     * @return
     */
    List<Role> findByUsername(String username);

    List<GrantedAuthority> getGrantedAuthorities(String username);

    List<Role> findByUserId(Integer userId);
}
