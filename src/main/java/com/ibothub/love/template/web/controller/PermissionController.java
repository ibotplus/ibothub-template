package com.ibothub.love.template.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibothub.love.template.adapter.PermissionAdapter;
import com.ibothub.love.template.model.vo.ResponseEntity;
import com.ibothub.love.template.model.vo.req.PermissionReq;
import com.ibothub.love.template.model.vo.resp.PermissionResp;
import com.ibothub.love.template.util.pageable.PageInfoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/16 10:21
 */
@Api(tags = "权限管理")
@Validated
@RestController
@RequestMapping("/api/auth/perm")
public class PermissionController {

    @Resource
    PermissionAdapter permissionAdapter;

    @ApiOperation(value = "创建权限", notes = "返回表示创建成功")
    @PostMapping("")
    public ResponseEntity create(@ApiParam(value = "UserReq Create RequestBody")
                                 @Validated(PermissionReq.Create.class)
                                 @RequestBody PermissionReq vo) {
        permissionAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @ApiOperation(value = "修改权限")
    @PutMapping("")
    public ResponseEntity update(@ApiParam(value = "UserReq Create RequestBody", type = "DatasourceVO")
                                 @Validated(PermissionReq.Update.class)
                                 @RequestBody PermissionReq vo) {
        permissionAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @ApiOperation(value = "根据id查询实体")
    @GetMapping("{id}")
    public ResponseEntity<PermissionResp> getOne(@PathVariable String id) {
        return ResponseEntity.ok(permissionAdapter.getById(Integer.valueOf(id)));
    }

    @ApiOperation(value = "删除权限")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@NotEmpty @PathVariable String id) {
        permissionAdapter.deleteById(Integer.valueOf(id));
        return ResponseEntity.ok();
    }

    @ApiOperation(value = "分页", notes = "返回查询结果")
    @PostMapping("/queryByPage")
    public ResponseEntity<IPage<PermissionResp>> queryByPage(@ApiParam(value = "Page Request", type = "PageRequest")
                                                       @Valid @RequestBody PageInfoRequest pageInfoRequest) {
        return ResponseEntity.ok(permissionAdapter.queryByPage(pageInfoRequest));
    }


}
