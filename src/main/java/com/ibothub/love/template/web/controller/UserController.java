package com.ibothub.love.template.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibothub.love.template.adapter.UserAdapter;
import com.ibothub.love.template.model.vo.ResponseEntity;
import com.ibothub.love.template.model.vo.req.UserCondition;
import com.ibothub.love.template.model.vo.req.UserReq;
import com.ibothub.love.template.model.vo.resp.UserResp;
import com.ibothub.love.template.util.AppContext;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * Controller
 * </p>
 *
 * @author yogurt_lei
 * @since 2019-06-19˙
 */
@Tag(name = "用户管理")
@Validated
@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    @Resource
    UserAdapter userAdapter;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "创建用户", description = "返回表示创建成功")
    @PostMapping("")
    public ResponseEntity create(@Schema(description = "UserReq Create RequestBody")
                                         @Validated(UserReq.Create.class)
                                         @RequestBody UserReq vo) {
        userAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "修改用户")
    @PutMapping("")
    public ResponseEntity update(@Schema(description = "UserReq Create RequestBody", type = "DatasourceVO")
                                 @Validated(UserReq.Update.class)
                                 @RequestBody UserReq vo) {
        userAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "根据id查询实体")
    @GetMapping("{id}")
    public ResponseEntity<UserResp> getOne(@PathVariable String id) {
        return ResponseEntity.ok(userAdapter.getById(Integer.valueOf(id)));
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@NotEmpty @PathVariable String id) {
        userAdapter.deleteById(Integer.valueOf(id));
        return ResponseEntity.ok();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "分页", description = "返回查询结果")
    @PostMapping("/queryByPage")
    public ResponseEntity<IPage<UserResp>> queryByPage(@Schema(description = "Page Request", type = "PageRequest")
                                             @Valid @RequestBody UserCondition pageInfoRequest) {
        return ResponseEntity.ok(userAdapter.queryByPage(pageInfoRequest));
    }

    @Operation(summary = "返回当前用户")
    @GetMapping()
    public ResponseEntity<UserResp> currentUser() {
        return ResponseEntity.ok(userAdapter.getByUsername(AppContext.ofUid()));
    }

    @Deprecated
    @Operation(summary = "根据条件查询用户", description = "返回查询结果")
    @PostMapping("/queryList")
    public ResponseEntity<List<UserResp>> queryList(@Schema(description = "Page Request", type = "PageRequest")
                                                        @RequestBody UserReq userReq) {
        return ResponseEntity.ok(userAdapter.queryList(userReq));
    }

}
