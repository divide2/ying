package com.mj.core.data.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 */
@ConfigurationProperties(prefix = "dic.surcharge-type")
@Component
public class SurchargeTypeProperties {

    private String general;
    private String special;

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}

