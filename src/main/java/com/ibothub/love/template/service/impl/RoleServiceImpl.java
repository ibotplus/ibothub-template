package com.ibothub.love.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.ibothub.love.template.dao.RoleMapper;
import com.ibothub.love.template.model.entity.Role;
import com.ibothub.love.template.service.RoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 16:33
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public List<Role> findByUsername(String username) {
        return roleMapper.findByUsername(username);
    }

    @Override
    public List<GrantedAuthority> getGrantedAuthorities(String username) {
        List<Role> roleList = roleMapper.findByUsername(username);
        List<String> authList = Lists.newArrayList();
        if (Objects.nonNull(roleList) && !roleList.isEmpty()){
            authList = roleList.stream()
                    .map(Role::getKey)
                    .collect(Collectors.toList());
        }
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(authList.toArray(new String[authList.size()]));
        if (authorityList.isEmpty()){
            authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorityList;
    }

}
