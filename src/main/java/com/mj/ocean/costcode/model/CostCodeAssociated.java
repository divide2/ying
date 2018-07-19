package com.mj.ocean.costcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author zejun
 * @date 2018/7/17 10:06
 */
@Data
@Entity
@Table(name = "ocean_fc_cost_code_associated")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CostCodeAssociated {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     *成本服务，成本类别,比如商务成本，业务成本，客户成本
     */
    @Column(name = "cost_service")
    private String costService;

    /**
     *柜型,20GP,40GP,40HC,45HC
     */
    @Column(name = "cabinet_type")
    private String cabinetType;

    /**
     *浮动金额
     */
    @Column(name = "floating_amount")
    private BigDecimal floatingAmount;

    /**
     *成本代码id
     */
    @Column(name = "cost_code_id")
    private Integer costCodeId;
}
