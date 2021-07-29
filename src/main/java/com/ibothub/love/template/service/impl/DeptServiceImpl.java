package com.ibothub.love.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ibothub.love.template.dao.DeptMapper;
import com.ibothub.love.template.model.entity.Dept;
import com.ibothub.love.template.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 19:15
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Override
    public List<Dept> findByUserId(Integer userId) {
        return getBaseMapper().findByUserId(userId);
    }
}
