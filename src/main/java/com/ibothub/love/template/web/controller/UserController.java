package com.ibothub.love.template.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibothub.love.template.adapter.UserAdapter;
import com.ibothub.love.template.model.vo.ResponseEntity;
import com.ibothub.love.template.model.vo.req.UserReq;
import com.ibothub.love.template.model.vo.resp.UserResp;
import com.ibothub.love.template.util.AppContext;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * Controller
 * </p>
 *
 * @author yogurt_lei
 * @since 2019-06-19˙
 */
@Api(tags = "用户管理")
@Validated
@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    @Resource
    UserAdapter userAdapter;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "创建用户", notes = "返回表示创建成功")
    @PostMapping("")
    public ResponseEntity create(@ApiParam(value = "UserReq Create RequestBody")
                                         @Validated(UserReq.Create.class)
                                         @RequestBody UserReq vo) {
        userAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "修改用户")
    @PutMapping("")
    public ResponseEntity update(@ApiParam(value = "UserReq Create RequestBody", type = "DatasourceVO")
                                 @Validated(UserReq.Update.class)
                                 @RequestBody UserReq vo) {
        userAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "根据id查询实体")
    @GetMapping("{id}")
    public ResponseEntity<UserResp> getOne(@PathVariable String id) {
        return ResponseEntity.ok(userAdapter.getById(Integer.valueOf(id)));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@NotEmpty @PathVariable String id) {
        userAdapter.deleteById(Integer.valueOf(id));
        return ResponseEntity.ok();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "分页", notes = "返回查询结果")
    @PostMapping("/queryByPage")
    public ResponseEntity<IPage<UserResp>> queryByPage(@ApiParam(value = "Page Request", type = "PageRequest")
                                             @Valid @RequestBody PageInfoRequest pageInfoRequest) {
        return ResponseEntity.ok(userAdapter.queryByPage(pageInfoRequest));
    }

    @ApiOperation(value = "返回当前用户")
    @GetMapping()
    public ResponseEntity<UserResp> currentUser() {
        return ResponseEntity.ok(userAdapter.getByUsername(AppContext.ofUid()));
    }

}
