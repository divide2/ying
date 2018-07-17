package com.mj.ocean.portcombination.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author zejun
 * @date 2018/7/16 17:16
 */
@Data
@Entity
@Table(name = "ocean_fc_port_combination_associated")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortCombinationAssociated {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "carrier_id")
    private Integer carrierId;

    @Column(name = "port_id")
    private Integer portId;

    @Column(name = "combination_id")
    private Integer combinationId;
}
