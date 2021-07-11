package com.ibothub.love.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ibothub.love.template.model.entity.User;
import com.ibothub.love.template.model.vo.req.UserReq;
import com.ibothub.love.template.model.vo.resp.UserResp;
import com.ibothub.love.template.util.pageable.PageInfo;
import com.ibothub.love.template.util.pageable.PageInfoRequest;

/**
 * <p>
 * simple demo for entity curd
 * </p>
 *
 * @author yogurt_lei
 * @since 2019-06-19
 */
public interface UserService extends IService<User> {

}
