package com.mj.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zejun
 * @date 2018/7/11 10:46
 */
public class UploadAliOSSUtils {
    private static final Logger logger = LoggerFactory.getLogger(UploadAliOSSUtils.class);

    private static String uoloadPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "upload/";

    public static String imageUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 上传文件信息
                logger.info("OriginalFilename：" + file.getOriginalFilename());
                logger.info("ContentType：" + file.getContentType());
                logger.info("Name：" + file.getName());
                logger.info("Size：" + file.getSize());
                //TODO:文件大小、名称、类型检查的业务处理

                // 检查上传目录
                File targetFile = new File(uoloadPath);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }

                // 实例化输出流
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(uoloadPath + file.getOriginalFilename()));
                out.write(file.getBytes());
                out.flush();
                out.close();

                // 上传到OSS
                String url = AliOSSUtil.uploadLocalFile(new File(uoloadPath + file.getOriginalFilename()), "upload/avatar/");
                if (url == null) {
                    //TODO:上传失败的业务处理
                    return "上传失败";
                }
                logger.info("上传完毕,访问地址:"+url);
                System.out.println(url);
                return url;
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("上传失败", e);
                return "上传失败";
            }
        }
        return "上传失败，因为文件是空的.";
    }

}
