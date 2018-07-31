package com.mj.core.data.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 */
@ConfigurationProperties(prefix = "dic.publish-status")
@Component
@Data
public class PublishStatusProperties {

    private String publish;
    private String draft;


}

