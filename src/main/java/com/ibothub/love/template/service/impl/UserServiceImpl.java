package com.ibothub.love.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ibothub.love.template.dao.UserMapper;
import com.ibothub.love.template.exception.WrongEntityIdException;
import com.ibothub.love.template.model.BeanConverter;
import com.ibothub.love.template.model.entity.User;
import com.ibothub.love.template.model.vo.req.UserReq;
import com.ibothub.love.template.model.vo.resp.UserResp;
import com.ibothub.love.template.service.UserService;
import com.ibothub.love.template.util.pageable.PageInfo;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import com.ibothub.love.template.util.pageable.PageUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yogurt_lei
 * @since 2019-06-19
 */
@Service
@CacheConfig(cacheNames = "User")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
