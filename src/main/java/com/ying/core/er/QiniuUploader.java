package com.ying.core.er;

import org.springframework.stereotype.Component;

/**
 * @author bvvy
 * @date 2018/10/11
 */
@Component
public class QiniuUploader {
    /*private Configuration cfg = new Configuration(Zone.zone2());
    private UploadManager uploadManager = new UploadManager(cfg);

    private final QiniuOssProperties properties;

    public QiniuUploader(QiniuOssProperties properties) {
        this.properties = properties;
    }

    *//**
     *
     * @param file 文件
     * @return 图片地址
     *//*
    public String imageUpload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ValidationException("file is empty");
        }
        Auth auth = Auth.create(properties.getAccessKey(), properties.getSecretKey());
        String upToken = auth.uploadToken(properties.getBucket());
        try {
            Response response = uploadManager.put(file.getInputStream(), null, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet result = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return String.format("%s/%s", properties.getBucketDomain(), result.key);
        } catch (QiniuException ex) {
            return ex.response.toString();
        } catch (IOException ex) {
            throw new SysException("wrong io");
        }
    }*/
}
