package com.ying.core.web.controller;

import com.ying.core.data.FileVO;
import com.ying.core.er.AliOssUploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author bvvy
 * @date 2018/7/17
 */
@RestController
@RequestMapping("/v1/upload/")
@Api(tags = "上传")
public class UploadController {

    private final AliOssUploader aliOssUploader;

    public UploadController(AliOssUploader aliOssUploader) {
        this.aliOssUploader = aliOssUploader;
    }

    @ApiOperation("图片上传")
    @PostMapping("/image")
    public ResponseEntity<FileVO> upload(MultipartFile file) {
        return ResponseEntity.ok(FileVO.of(aliOssUploader.imageUpload(file)));
    }
}
