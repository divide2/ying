package com.divide2.core.data.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 */
@ConfigurationProperties(prefix = "dic.status")
@Component
@Data
public class StatusProperties {

    private Character enable;
    private Character disable;

}

