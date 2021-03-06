package com.divide2.team.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bvvy
 * @date 2019/2/26
 */
@Data
@Entity
@Table(name = "t_workbench")
public class Workbench {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String teamId;

    private String menuGroupId;

    private String menuId;

    private Integer orderNum;

}
