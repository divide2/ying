package com.ying.core.data.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 * @date 2019/3/11
 */

@ConfigurationProperties(prefix = "divide.dic.type.stock")
@Component
@Data
public class StockTypeProperties {

    private String deliver;
    private String receive;
}
