package com.mj.core.web.controller;

import com.mj.core.data.FileVO;
import com.mj.core.er.AliOssUploader;
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
public class UploadController {

    private final AliOssUploader aliOssUploader;

    public UploadController(AliOssUploader aliOssUploader) {
        this.aliOssUploader = aliOssUploader;
    }

    @PostMapping("/image")
    public ResponseEntity<FileVO> upload(MultipartFile file) {
        return ResponseEntity.ok(FileVO.of(aliOssUploader.imageUpload(file)));
    }
}
