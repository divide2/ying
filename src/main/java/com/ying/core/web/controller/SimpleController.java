package com.ying.core.web.controller;

import com.ying.core.data.FileVO;
import com.ying.core.data.VerificationCode;
import com.ying.core.er.AliOssUploader;
import com.ying.core.er.QiniuUploader;
import com.ying.core.er.VerificationCodeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author bvvy
 * @date 2018/7/17
 */
@RestController
@Api(tags = "基本")
public class SimpleController {

    private final AliOssUploader aliOssUploader;
    private final QiniuUploader qiniuUploader;
    private final VerificationCodeManager verificationCodeManager = new VerificationCodeManager();

    public SimpleController(AliOssUploader aliOssUploader,
                            QiniuUploader qiniuUploader) {
        this.aliOssUploader = aliOssUploader;
        this.qiniuUploader = qiniuUploader;
    }

    @ApiOperation("图片上传")
    @PostMapping("/v1/upload/image")
    public ResponseEntity<FileVO> upload(@RequestPart MultipartFile file) {
        return ResponseEntity.ok(FileVO.of(qiniuUploader.imageUpload(file)));
    }

    @ApiOperation("获取验证码")
    @GetMapping("/v1/code/verification")
    public ResponseEntity<VerificationCode> getVerifyCode(String phoneNumber) {
        VerificationCode code = verificationCodeManager.generate(phoneNumber);
        return ResponseEntity.ok(code);
    }

}
