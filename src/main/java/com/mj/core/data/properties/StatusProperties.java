package com.mj.core.data.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 */
@ConfigurationProperties(prefix = "dic.status")
@Component
public class StatusProperties {

    private String enable;
    private String disable;

    public String getEnable() {
        return enable;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}

