package com.ibothub.love.template.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibothub.love.template.model.dto.UserRoleDTO;
import com.ibothub.love.template.model.entity.User;
import com.ibothub.love.template.model.vo.req.UserCondition;
import com.ibothub.love.template.model.vo.req.UserReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version 1.0
 * @date 2021/7/6 21:55
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<User> selectPage(IPage<User> page, @Param("userCondition") UserCondition userCondition);

    List<User> queryList(@Param("userReq") UserReq userReq);

    List<UserRoleDTO> selectByRoleIds(@Param(("roleIdList")) List<Integer> roleIdList);

}
