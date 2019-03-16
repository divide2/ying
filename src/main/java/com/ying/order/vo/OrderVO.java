package com.ying.order.vo;

import com.ying.team.vo.TeamVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
public class OrderVO {

    public OrderVO(String id, String orderId, Integer fromId, String fromName, String fromTeamId, String toTeamId, String orderNo, BigDecimal earnestMoney, BigDecimal balancePayment, LocalDateTime createTime, LocalDate deliveryDate, String remarks, String attachment, String status) {
        this.id = id;
        this.orderId = orderId;
        this.fromId = fromId;
        this.fromName = fromName;
        this.fromTeamId = fromTeamId;
        this.toTeamId = toTeamId;
        this.orderNo = orderNo;
        this.earnestMoney = earnestMoney;
        this.balancePayment = balancePayment;
        this.createTime = createTime;
        this.deliveryDate = deliveryDate;
        this.remarks = remarks;
        this.attachment = attachment;
        this.status = status;
    }

    public OrderVO() {
    }

    @ApiModelProperty("这不是order的id")
    private String id;
    private String orderId;

    private Integer fromId;

    private String fromName;

    private String fromTeamId;

    private String toTeamId;

    private TeamVO team;

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
}
