package com.ibothub.love.template.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibothub.love.template.model.BeanConverter;
import com.ibothub.love.template.model.entity.User;
import com.ibothub.love.template.model.vo.ResponseEntity;
import com.ibothub.love.template.model.vo.req.UserReq;
import com.ibothub.love.template.model.vo.resp.UserResp;
import com.ibothub.love.template.service.UserService;
import com.ibothub.love.template.util.pageable.PageInfo;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Controller
 * </p>
 *
 * @author yogurt_lei
 * @since 2019-06-19˙
 */
@Api(description = "用户接口", tags = "sys")
@Validated
@RestController
@RequestMapping("/sys/")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    BeanConverter beanConverter;

    @ApiOperation(value = "创建用户", notes = "返回表示创建成功")
    @PostMapping("/user")
    public ResponseEntity create(@ApiParam(value = "UserReq Create RequestBody")
                                         @Validated(UserReq.Create.class)
                                         @RequestBody UserReq vo) {
        userService.saveOrUpdate(beanConverter.forward(vo));
        return ResponseEntity.ok();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@NotEmpty @PathVariable String id) {
        userService.removeById(id);
        return ResponseEntity.ok();
    }

    @ApiOperation(value = "修改用户")
    @PutMapping("/user")
    public ResponseEntity update(@ApiParam(value = "UserReq Create RequestBody", type = "DatasourceVO")
                                          @Validated(UserReq.Update.class)
                                          @RequestBody UserReq vo) {
        userService.saveOrUpdate(beanConverter.forward(vo));
        return ResponseEntity.ok();
    }

    @ApiOperation(value = "根据id查询实体")
    @GetMapping("{id}")
    public ResponseEntity<UserResp> getOne(@PathVariable String id) {
        return ResponseEntity.ok(beanConverter.backward(userService.getById(id)));
    }

    @ApiOperation(value = "分页", notes = "返回查询结果")
    @PostMapping("/users")
    public ResponseEntity<IPage<UserResp>> list(@ApiParam(value = "Page Request", type = "PageRequest")
                                             @Valid @RequestBody PageInfoRequest pageInfoRequest) {
        IPage<User> pageParam = new Page<>(pageInfoRequest.getPageNum(), pageInfoRequest.getPageSize());
        IPage<User> pageResult = userService.page(pageParam);
        IPage<UserResp> page = pageResult.convert(beanConverter::backward);
        return ResponseEntity.ok(page);
    }

}
