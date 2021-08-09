package com.ibothub.love.template.web.controller;

import com.ibothub.love.template.adapter.ConfAdapter;
import com.ibothub.love.template.model.vo.ResponseEntity;
import com.ibothub.love.template.model.vo.req.ConfReq;
import com.ibothub.love.template.model.vo.resp.ConfResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/7/14 16:17
 */

@Api(tags = "系统配置管理")
@Validated
@RestController
@RequestMapping("/api/sys/conf")
public class ConfController {

    @Resource
    ConfAdapter confAdapter;

    @ApiOperation(value = "修改")
    @PutMapping("")
    public ResponseEntity update(@ApiParam(value = "ConfReq Create RequestBody", type = "VO")
                                 @Validated(ConfReq.Update.class)
                                 @RequestBody ConfReq[] vo) {
        confAdapter.saveOrUpdate(vo);
        return ResponseEntity.ok();
    }

    @ApiOperation(value = "根据id查询实体")
    @GetMapping("{code}")
    public ResponseEntity<ConfResp> getOne(@PathVariable String code) {
        return ResponseEntity.ok(confAdapter.getByCode(code));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@NotEmpty @PathVariable String id) {
        confAdapter.deleteById(Integer.valueOf(id));
        return ResponseEntity.ok();
    }

    @ApiOperation(value = "列表", notes = "返回查询结果")
    @PostMapping("/queryList")
    public ResponseEntity<List<ConfResp>> queryList(@ApiParam(value = "Request", type = "query list")
                                                    @Valid @RequestBody ConfReq vo) {
        return ResponseEntity.ok(confAdapter.queryList(vo));
    }

    @ApiOperation(value = "列表", notes = "返回查询结果")
    @PostMapping("/permitList")
    public ResponseEntity<List<ConfResp>> permitList() {
        return ResponseEntity.ok(confAdapter.queryPermitList());
    }
}
