package com.ying.core.er;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.ying.core.data.properties.QiniuOssProperties;
import com.ying.core.exception.SysException;
import com.ying.core.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author bvvy
 * @date 2018/10/11
 */
@Component
public class QiniuUploader {
    private Configuration cfg = new Configuration(Zone.zone0());
    private UploadManager uploadManager = new UploadManager(cfg);

    private final QiniuOssProperties properties;

    public QiniuUploader(QiniuOssProperties properties) {
        this.properties = properties;
    }

   /*
     *
     * @param file 文件
     * @return 图片地址
     */
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
    }
}
