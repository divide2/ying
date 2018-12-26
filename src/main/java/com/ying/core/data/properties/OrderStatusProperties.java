package com.ying.core.data.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bvvy
 * @date 2018/12/17
 */
@ConfigurationProperties(prefix = "divide.dic.status.order")
@Component
@Data
public class OrderStatusProperties {

    private String waitingConfirm;
    private String waitingDeliver;
    private String waitingReceive;
    private String finish;
}
