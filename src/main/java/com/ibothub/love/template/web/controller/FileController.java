package com.ibothub.love.template.web.controller;

import com.ibothub.love.template.model.vo.ResponseEntity;
import com.ibothub.love.template.util.FileUploadUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Tag(name = "文件上传")
@RestController
@RequestMapping("/file")
public class FileController {

    @Operation(summary = "上传文件")
    @Parameters({
            @Parameter(name = "file", description = "文件", schema = @Schema(type = "file"))
    })
    @PostMapping(
            value = "/",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity upload(MultipartFile file) throws IOException {
        String voice = FileUploadUtil.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
        return ResponseEntity.ok(voice);
    }

    @Operation(summary = "删除文件")
    @Parameters({
            @Parameter(name = "filePath", description = "文件路径", required = true)
    })
    @DeleteMapping("/")
    public ResponseEntity delete(String filePath) {
        return ResponseEntity.ok(FileUploadUtil.delete(filePath));
    }


}
