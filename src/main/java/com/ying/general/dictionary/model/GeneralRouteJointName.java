package com.ying.general.dictionary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author zejun
 * @date 2018/7/27 18:31
 */
@Data
@Entity
@Table(name = "general_route_joint_name")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralRouteJointName {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 航线统称
     */
    @Column(name = "route_joint_name")
    private String routeJointName;
}
