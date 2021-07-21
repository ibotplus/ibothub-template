package com.ibothub.love.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ibothub.love.template.dao.UserRoleMapper;
import com.ibothub.love.template.model.entity.UserRole;
import com.ibothub.love.template.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 19:15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
