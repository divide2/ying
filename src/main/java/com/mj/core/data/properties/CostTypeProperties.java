package com.mj.core.data.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 */
@ConfigurationProperties(prefix = "dic.cost-type")
@Component
@Data
public class CostTypeProperties {

    private String general;
    private String special;

}

