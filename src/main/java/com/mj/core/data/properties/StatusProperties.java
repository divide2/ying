package com.mj.core.data.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 */
@ConfigurationProperties(prefix = "dic.status")
@Component
public class StatusProperties {

    private Character enable;
    private Character disable;

    public Character getEnable() {
        return enable;
    }

    public Character getDisable() {
        return disable;
    }

    public void setDisable(Character disable) {
        this.disable = disable;
    }

    public void setEnable(Character enable) {
        this.enable = enable;
    }
}

