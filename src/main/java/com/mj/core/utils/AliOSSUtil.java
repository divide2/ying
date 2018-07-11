package com.mj.core.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @auther: zejun
 * @date: 2018/7/11 15:19
 */
public class AliOSSUtil {
    private static final Logger logger = LoggerFactory.getLogger(AliOSSUtil.class);

    private static String END_POINT = "http://oss-cn-shenzhen.aliyuncs.com";
    private static String ACCESS_KEY_ID  = "LTAIHxopb8AXx7iF";
    private static String ACCESS_KEY_SECRET  = "b2PrRJQgxCeMqhGL1y9QkKVebBV0rJ";
    private static String BUCKET_NAME  = "mj-ydt";
    private static String FILE_HOST  = "http://mj-ydt.oss-cn-shenzhen.aliyuncs.com/";

    /**
     * 上传文件到bucket
     *
     * @param file     本地文件
     * @param dir      bucket存放目录(末尾要加/)
     * @param fileName 上传文件名
     * @return 访问地址
     */
    public static String uploadLocalFile(File file, String dir, String fileName) {
        if (file == null || !file.exists()) {
            return null;
        }
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, dir + fileName, file));
            if (null != result) {
                return FILE_HOST + dir + fileName;
            } else {
                return null;
            }
        } catch (OSSException | ClientException oe) {
            logger.error("上传OSS失败:", oe);
            oe.printStackTrace();
            return null;
        } finally {
            // 关闭OSS服务
            ossClient.shutdown();
        }
    }

    /**
     * 上传文件到bucket
     *
     * @param file 本地文件
     * @param dir  bucket目录
     * @return 访问地址
     */
    public static String uploadLocalFile(File file, String dir) {
        if (file == null) {
            return null;
        }
        String filePath = dir + file.getName();
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, filePath, file));
            if (null != result) {
                // 拼装访问地址
                return FILE_HOST + filePath;
            } else {
                return null;
            }
        } catch (OSSException | ClientException oe) {
            logger.error("OSS上传失败:", oe);
            oe.printStackTrace();
            return null;
        } finally {
            // 关闭OSS服务
            ossClient.shutdown();
        }
    }

}
