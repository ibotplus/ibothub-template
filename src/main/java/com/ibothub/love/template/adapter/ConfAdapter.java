package com.ibothub.love.template.adapter;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.ibothub.love.template.model.BeanConverter;
import com.ibothub.love.template.model.entity.Conf;
import com.ibothub.love.template.model.vo.req.ConfReq;
import com.ibothub.love.template.model.vo.resp.ConfResp;
import com.ibothub.love.template.service.ConfService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/15 17:48
 */
@Component
public class ConfAdapter {

    @Resource
    ConfService confService;

    @Resource
    BeanConverter beanConverter;

    public void saveOrUpdate(ConfReq[] vo) {
        List<Conf> list = Arrays.stream(vo)
                .peek(item->{
                    if (item.getType()==9){
                        // 如果是图片，那么获取 raw

                        // 如果 raw 不为空，那么通过base64转成图片

                        // 通过 vfs 上传至目标存储器

                        // 返回一个可访问的路径，设置到 item.value 中
                    }
                })
                .map(beanConverter::forward)
                .collect(Collectors.toList());
        confService.saveOrUpdateBatch(list);
    }

    public ConfResp getByCode(String code) {
        Conf conf = confService.getOne(Wrappers.<Conf>lambdaQuery().eq(Conf::getCode, code));
        if (conf!=null){
            return beanConverter.backward(conf);
        }
        return null;
    }

    public void deleteById(Integer id) {
        confService.removeById(id);
    }

    public List<ConfResp> queryList(ConfReq vo) {
        List<Conf> list = confService.list();
        return beanConverter.backward(list);
    }

    public List<ConfResp> queryPermitList() {
        List<String> codeList = Lists.newArrayList("title", "loginTitle", "loginLogo", "loginBG", "favTitle", "favIcon");
        List<Conf> list = confService.list(Wrappers.<Conf>lambdaQuery().in(Conf::getCode, codeList));
        return beanConverter.backward(list);
    }
}
