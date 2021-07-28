package com.ibothub.love.template.adapter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibothub.love.template.model.BeanConverter;
import com.ibothub.love.template.model.entity.Dept;
import com.ibothub.love.template.model.vo.req.DeptReq;
import com.ibothub.love.template.model.vo.resp.DeptResp;
import com.ibothub.love.template.service.DeptService;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/15 17:48
 */
@Component
public class DeptAdapter {

    @Resource
    DeptService deptService;

    @Resource
    BeanConverter beanConverter;

    public void saveOrUpdate(DeptReq vo) {
        Dept dept = beanConverter.forward(vo);
        if (StringUtils.isNotBlank(dept.getParentId())) {
            Dept parentDept = deptService.getById(dept.getParentId());
            dept.setPath(parentDept.getPath() + "." + parentDept.getId());
        }
        deptService.saveOrUpdate(dept);
    }

    public DeptResp getById(Integer id) {
        return beanConverter.backward(deptService.getById(id));
    }

    public void deleteById(Integer id) {
        deptService.removeById(id);
    }

    public IPage<DeptResp> queryByPage(PageInfoRequest pageInfoRequest) {
        IPage<Dept> pageParam = new Page<>(pageInfoRequest.getPageNum(), pageInfoRequest.getPageSize());
        IPage<Dept> pageResult = deptService.page(pageParam);
        return pageResult.convert(beanConverter::backward);
    }

    public List<DeptResp> queryList(DeptReq vo) {
        if (StringUtils.isNotBlank(vo.getName())){
            return beanConverter.backward(deptService.lambdaQuery()
                    .like(Dept::getName, vo.getName())
                    .select(Dept::getName, Dept::getId, Dept::getParentId)
                    .list());
        }else{
            return beanConverter.backward(deptService.lambdaQuery()
                    .select(Dept::getName, Dept::getId, Dept::getParentId)
                    .list());
        }
    }
}
