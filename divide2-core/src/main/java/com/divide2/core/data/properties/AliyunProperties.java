package com.divide2.core.data.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 * @date 2018/7/17
 */
@ConfigurationProperties(prefix = "aliyun")
@Component
@Data
public class AliyunProperties {


    private String accessKeyId;

    private String accessKeySecret;

    private Oss oss;

    @Data
    public static class Oss {
        private String endPoint;

        private String bucketName;

        private String fileHost;
    }


}
