package com.ying.order.vo;

import com.ying.order.model.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
    @ApiModelProperty("这不是order的id")
    private String id;
    private String orderId;
    private Integer toId;

    private String toName;

    private Integer fromId;

    private String fromName;

    private String orderNo;
    /**
     * 定金
     */
    private BigDecimal earnestMoney;

    /**
     * 尾款
     */
    private BigDecimal balancePayment;

    private LocalDateTime createTime;

    /**
     * 交付日期
     */
    private LocalDate deliveryDate;

    private String remarks;

    private String attachment;

    private String status;

    public static OrderVO from(Order order) {
        return new OrderVO(
                order.getId(),
                order.getId(),
                order.getToId(),
                order.getToName(),
                order.getFromId(),
                order.getFromName(),
                order.getOrderNo(),
                order.getEarnestMoney(),
                order.getBalancePayment(),
                order.getCreateTime(),
                order.getDeliveryDate(),
                order.getRemarks(),
                order.getAttachment(),
                order.getStatus()
        );
    }
}
