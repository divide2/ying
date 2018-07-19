package com.mj.ocean.quotation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zejun
 * @date 2018/7/17 17:54
 */
@Data
@Entity
@Table(name = "ocean_fc_quotation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quotation {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 船公司id
     */
    @Column(name = "carrier_id")
    private Integer carrierId;

    /**
     * 船公司code
     */
    @Column(name = "carrier_code")
    private String carrierCode;

    /**
     * 航线id
     */
    @Column(name = "route_id")
    private Integer routeId;

    /**
     * 航线代码
     */
    @Column(name = "route_code")
    private String routeCode;

    /**
     * 起运港口id
     */
    @Column(name = "port_shipment_id")
    private Integer portShipmentId;

    /**
     * 起运港口
     */
    @Column(name = "port_shipment")
    private String portShipment;

    /**
     * 起运港组合id
     */
    @Column(name = "port_shipment_combination_id")
    private Integer portShipmentCombinationId;

    /**
     * 目的港口id
     */
    @Column(name = "port_destination_id")
    private Integer portDestinationId;

    /**
     * 目的港口
     */
    @Column(name = "port_destination")
    private String portDestination;

    /**
     * 目的港组合id
     */
    @Column(name = "port_destination_combination_id")
    private Integer portDestinationCombinationId;

    /**
     * 截关时间
     */
    @Column(name = "closing_time")
    private LocalDateTime closingTime;

    /**
     * 开船时间
     */
    @Column(name = "sailing_time")
    private LocalDateTime sailingTime;

    /**
     * 中转港口
     */
    @Column(name = "transit_port")
    private String transitPort;

    /**
     * 航程
     */
    private BigDecimal voyage;

    /**
     * 币种
     */
    private String currency;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 有效期
     */
    @Column(name = "yerm_validity")
    private String yermValidity;

    /**
     * 成本代码id
     */
    @Column(name = "cost_id")
    private Integer costId;

    /**
     * 成本代码code
     */
    @Column(name = "cost_code")
    private String costCode;

    /**
     * 创建人id
     */
    @Column(name = "created_userid")
    private Integer createdUserid;

    /**
     * 创建人名称
     */
    @Column(name = "created_username")
    private String createdUsername;

    /**
     * 创建时间
     */
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    /**
     * 修改人id
     */
    @Column(name = "update_userid")
    private Integer updateUserid;

    /**
     * 修改人名称
     */
    @Column(name = "update_username")
    private String updateUsername;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    /**
     * 是否删除。 默认N；N：未删除；Y：删除
     */
    @Builder.Default
    private Character deleted = 'N';

    /**
     * 是否启用。 默认Y；Y：启用；N：不可用
     */
    @Builder.Default
    private Character enabled = 'Y';

    /**
     * 是否发布。 Y：发布；N：不发布
     */
    private Character publish;
}
