package com.mj.ocean.costcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author zejun
 * @date 2018/7/17 09:59
 */
@Data
@Entity
@Table(name = "ocean_fc_cost_code")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CostCode {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 成本代码code
     */
    private String code;

    /**
     * 是否启用。 默认Y；Y：启用；N：不可用
     */
    @Builder.Default
    private Character enabled = 'Y';

    /**
     * 客户公司id
     */
    @Column(name = "company_id")
    private Integer companyId;

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
     * 修改人时间
     */
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
