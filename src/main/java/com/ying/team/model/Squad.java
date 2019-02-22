package com.ying.team.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 团队里面的人员小分队
 * @author bvvy
 * <p>
 */
@Data
@Entity
@Table(name = "t_squad")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Squad {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String teamId;

    /**
     * 小分队名称
     */
    private String name;
}
