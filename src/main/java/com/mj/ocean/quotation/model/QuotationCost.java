package com.mj.ocean.quotation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author zejun
 * @date 2018/7/17 18:16
 */
@Data
@Entity
@Table(name = "ocean_fc_quotation_cost_service")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuotationCost {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 报价主表id
     */
    @Column(name = "quotation_id")
    private Integer quotationId;

    /**
     * 船东成本价
     */
    @Column(name = "original_price")
    private String originalPrice;

    /**
     * 商务成本价
     */
    @Column(name = "commerce_price")
    private String commercePrice;

    /**
     * 业务成本价
     */
    @Column(name = "business_price")
    private String businessPrice;

    /**
     * 公开展示价
     */
    @Column(name = "open_price")
    private String openPrice;

    /**
     * 柜型: 20GP，40GP，40HC，45HC
     */
    private String type;

}
