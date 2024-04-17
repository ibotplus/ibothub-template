package com.ibothub.love.template.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibothub.love.template.adapter.PermissionAdapter;
import com.ibothub.love.template.model.vo.ResponseEntity;
import com.ibothub.love.template.model.vo.req.PermissionReq;
import com.ibothub.love.template.model.vo.resp.PermissionResp;
import com.ibothub.love.template.util.AppContext;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/16 10:21
 */
@Tag(name = "权限管理")
@Validated
@RestController
@RequestMapping("/api/auth/perm")
public class PermissionController {

    @Resource
    PermissionAdapter permissionAdapter;

    @Operation(summary = "创建权限", description = "返回表示创建成功")
    @PostMapping("")
    public ResponseEntity create(@Schema(description = "UserReq Create RequestBody")
                                 @Validated(PermissionReq.Create.class)
                                 @RequestBody PermissionReq vo) {
        permissionAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @Operation(summary = "修改权限")
    @PutMapping("")
    public ResponseEntity update(@Schema(description = "UserReq Create RequestBody", type = "DatasourceVO")
                                 @Validated(PermissionReq.Update.class)
                                 @RequestBody PermissionReq vo) {
        permissionAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @Operation(summary = "根据id查询实体")
    @GetMapping("{id}")
    public ResponseEntity<PermissionResp> getOne(@PathVariable String id) {
        return ResponseEntity.ok(permissionAdapter.getById(Integer.valueOf(id)));
    }

    @Operation(summary = "删除权限")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@NotEmpty @PathVariable String id) {
        permissionAdapter.deleteById(Integer.valueOf(id));
        return ResponseEntity.ok();
    }

    @Operation(summary = "分页", description = "返回查询结果")
    @PostMapping("/queryByPage")
    public ResponseEntity<IPage<PermissionResp>> queryByPage(@Schema(description = "Page Request", type = "PageRequest")
                                                       @Valid @RequestBody PageInfoRequest pageInfoRequest) {
        return ResponseEntity.ok(permissionAdapter.queryByPage(pageInfoRequest));
    }

    @Operation(summary = "查询权限", description = "返回查询结果")
    @PostMapping("/queryList")
    public ResponseEntity<List<PermissionResp>> queryList(@Schema(description = "Request", type = "Request")
                                                    @RequestBody PermissionReq permReq) {
        return ResponseEntity.ok(permissionAdapter.queryList(permReq));
    }

    @Operation(summary = "返回当前用户的权限路径")
    @PostMapping("/routes")
    public ResponseEntity<List<PermissionResp>> routes() {
        return ResponseEntity.ok(permissionAdapter.getByUsername(AppContext.ofUid()));
    }

}
