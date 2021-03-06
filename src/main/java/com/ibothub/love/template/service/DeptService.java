package com.ibothub.love.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ibothub.love.template.model.entity.Dept;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 16:32
 */
public interface DeptService extends IService<Dept> {

    List<Dept> findByUserId(Integer userId);
}
