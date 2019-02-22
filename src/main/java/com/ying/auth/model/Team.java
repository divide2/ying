package com.ying.auth.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author bvvy
 * @date 2018/12/4
 */
@Entity
@Table(name = "t_team")
@Data
public class Team {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String name;

    private String image;
}
