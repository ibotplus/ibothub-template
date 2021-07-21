package com.ibothub.love.template.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ibothub.love.template.adapter.RoleAdapter;
import com.ibothub.love.template.model.vo.ResponseEntity;
import com.ibothub.love.template.model.vo.req.RoleReq;
import com.ibothub.love.template.model.vo.resp.RoleResp;
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
 * @date 2021/7/14 16:17
 */

@Api(tags = "角色管理")
@Validated
@RestController
@RequestMapping("/api/auth/role")
public class RoleController {

    @Resource
    RoleAdapter roleAdapter;

    @ApiOperation(value = "创建角色", notes = "返回表示创建成功")
    @PostMapping("")
    public ResponseEntity create(@ApiParam(value = "UserReq Create RequestBody")
                                 @Validated(RoleReq.Create.class)
                                 @RequestBody RoleReq vo) {
        roleAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("")
    public ResponseEntity update(@ApiParam(value = "UserReq Create RequestBody", type = "DatasourceVO")
                                 @Validated(RoleReq.Update.class)
                                 @RequestBody RoleReq vo) {
        roleAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @ApiOperation(value = "根据id查询实体")
    @GetMapping("{id}")
    public ResponseEntity<RoleResp> getOne(@PathVariable String id) {
        return ResponseEntity.ok(roleAdapter.getById(Integer.valueOf(id)));
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@NotEmpty @PathVariable String id) {
        roleAdapter.deleteById(Integer.valueOf(id));
        return ResponseEntity.ok();
    }

    @ApiOperation(value = "分页", notes = "返回查询结果")
    @PostMapping("/queryByPage")
    public ResponseEntity<IPage<RoleResp>> queryByPage(@ApiParam(value = "Page Request", type = "PageRequest")
                                                       @Valid @RequestBody PageInfoRequest pageInfoRequest) {
        return ResponseEntity.ok(roleAdapter.queryByPage(pageInfoRequest));
    }
}
