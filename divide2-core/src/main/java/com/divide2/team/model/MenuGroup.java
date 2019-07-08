package com.divide2.team.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜单分组
 * @author bvvy
 * @date 2019/2/26
 */
@Data
@Entity
@Table(name = "t_menu_group")
public class MenuGroup {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String teamId;

    private String name;

    private Integer orderNum;

}
