package com.ying.core.data.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 * @date 2018/10/11
 */
@ConfigurationProperties(prefix = "qiniu.oss")
@Component
@Data
public class QiniuOssProperties {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String bucketDomain;
}
