package com.mj.core.er;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.mj.core.data.properties.AliOssProperties;
import com.mj.core.exception.SysException;
import com.mj.core.exception.ValidationException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import static com.mj.core.val.Punctuation.DOT;
import static com.mj.core.val.Punctuation.SLASH;

/**
 * @author bvvy
 * @date 2018/7/17
 */
@Component
public class AliOssUploader {

    private static final String GIF = "gif";
    private final AliOssProperties properties;

    public AliOssUploader(AliOssProperties properties) {
        this.properties = properties;
    }

    /**
     * 上传图片
     *
     * @param file file
     * @return file
     */
    public String imageUpload(MultipartFile file) {
        try {
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            //排除gif文件,避免bug
            if (GIF.equalsIgnoreCase(ext)) {
                throw new ValidationException("no_gif_image");
            }
            InputStream inputStream = file.getInputStream();
            // 验证是不是图片文件
            BufferedImage bi = ImageIO.read(inputStream);
            if (bi == null) {
                throw new ValidationException("not_image");
            }
            UploadKeeper keeper = new UploadKeeper(inputStream, file.getBytes(), "image", ext);
            return this.upload(keeper);
        } catch (IOException e) {
            throw new SysException(e.getMessage());
        }
    }

    /**
     * 上传
     *
     * @param keeper keeper
     * @return file path
     */
    private String upload(UploadKeeper keeper) {

        OSSClient ossClient = new OSSClient(properties.getEndPoint(), properties.getAccessKeyId(), properties.getSecretAccessKey());
        try {
            PutObjectResult result = ossClient.putObject(
                    new PutObjectRequest(properties.getBucketName(),
                            keeper.getPath(),
                            new ByteArrayInputStream(keeper.getBytes())));
            if (result == null) {
                throw new SysException("upload_fail");
            }
            return properties.getFileHost() + keeper.path;
        } catch (OSSException | ClientException e) {
            throw new SysException(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }

    private static class UploadKeeper {
        private String md5;
        private InputStream inputStream;
        private byte[] bytes;
        private String path;
        private String fileType;

        public UploadKeeper(InputStream inputStream, byte[] bytes, String prefix, String fileType) {
            this.inputStream = inputStream;
            this.bytes = bytes;
            this.md5 = DigestUtils.md5Hex(bytes);
            this.fileType = fileType;
            this.path = generatePath(prefix);
        }

        public String getPath() {
            return path;
        }

        public String getMd5() {
            return md5;
        }

        public InputStream getInputStream() {
            return inputStream;
        }

        public void setInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public byte[] getBytes() {
            return bytes;
        }

        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }

        private String generatePath(String prefix) {
            return prefix
                    + SLASH + LocalDate.now().getYear() + SLASH
                    + this.md5.substring(0, 1)
                    + SLASH + this.md5.substring(1)
                    + DOT + fileType;
        }
    }
}
