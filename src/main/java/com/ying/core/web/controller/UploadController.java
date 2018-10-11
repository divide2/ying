package com.ying.core.web.controller;

import com.ying.core.data.FileVO;
import com.ying.core.er.AliOssUploader;
import com.ying.core.er.QiniuUploader;
import com.ying.core.exception.ValidationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
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
    private final QiniuUploader qiniuUploader;

    public UploadController(AliOssUploader aliOssUploader, QiniuUploader qiniuUploader) {
        this.aliOssUploader = aliOssUploader;
        this.qiniuUploader = qiniuUploader;
    }

    @ApiOperation("图片上传")
    @PostMapping("/image")
    public ResponseEntity<FileVO> upload(@RequestPart MultipartFile file) {

        return ResponseEntity.ok(FileVO.of(qiniuUploader.imageUpload(file)));
    }
}
