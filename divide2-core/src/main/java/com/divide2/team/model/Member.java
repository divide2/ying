package com.divide2.team.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author bvvy
 * <p>
 * 团队小分队下的人员
 */
@Entity
@Table(name = "t_member")
@Data
public class Member {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    /**
     * 团队的id
     */
    private String teamId;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 所在分队
     */
    private String squadId;


    /**
     * 团队里备注
     */
    private String memoName;

    /**
     * 职位
     */
    private String position;
}
