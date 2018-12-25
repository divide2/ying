package com.ying.core.data.properties;

import com.ying.core.data.properties.bean.Defaults;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author bvvy
 * @date 2018/8/10
 */
@ConfigurationProperties(prefix = "divide")
@Component
@Data
public class DivideProperties {

    private Map<String, Map<String, Map<String, String>>> dic;
    private Defaults defaults;
}
