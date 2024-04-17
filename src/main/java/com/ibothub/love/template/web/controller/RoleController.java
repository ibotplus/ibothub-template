package com.ibothub.love.template.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibothub.love.template.adapter.RoleAdapter;
import com.ibothub.love.template.model.vo.ResponseEntity;
import com.ibothub.love.template.model.vo.req.RoleReq;
import com.ibothub.love.template.model.vo.resp.RoleResp;
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
 * @date 2021/7/14 16:17
 */

@Tag(name = "角色管理")
@Validated
@RestController
@RequestMapping("/api/auth/role")
public class RoleController {

    @Resource
    RoleAdapter roleAdapter;

    @Operation(summary = "创建角色", description = "返回表示创建成功")
    @PostMapping("")
    public ResponseEntity create(@Schema(description = "UserReq Create RequestBody")
                                 @Validated(RoleReq.Create.class)
                                 @RequestBody RoleReq vo) {
        roleAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @Operation(summary = "修改角色")
    @PutMapping("")
    public ResponseEntity update(@Schema(description = "RoleReq Create RequestBody", type = "DatasourceVO")
                                 @Validated(RoleReq.Update.class)
                                 @RequestBody RoleReq vo) {
        roleAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @Operation(summary = "根据id查询实体")
    @GetMapping("{id}")
    public ResponseEntity<RoleResp> getOne(@PathVariable String id) {
        return ResponseEntity.ok(roleAdapter.getById(Integer.valueOf(id)));
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@NotEmpty @PathVariable String id) {
        roleAdapter.deleteById(Integer.valueOf(id));
        return ResponseEntity.ok();
    }

    @Operation(summary = "分页", description = "返回查询结果")
    @PostMapping("/queryByPage")
    public ResponseEntity<IPage<RoleResp>> queryByPage(@Schema(description = "Page Request", type = "PageRequest")
                                                       @Valid @RequestBody PageInfoRequest pageInfoRequest) {
        return ResponseEntity.ok(roleAdapter.queryByPage(pageInfoRequest));
    }

    @Operation(summary = "列表", description = "返回查询结果")
    @PostMapping("/queryList")
    public ResponseEntity<List<RoleResp>> queryList(@Schema(description = "Request", type = "query list")
                                                       @Valid @RequestBody RoleReq vo) {
        return ResponseEntity.ok(roleAdapter.queryList(vo));
    }
}
