package com.mj.core.data.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author bvvy
 * @date 2018/8/10
 */
@ConfigurationProperties
@Component
@Data
public class DicProperties {

    private Map<String, Map<String, String>> dic;

}
