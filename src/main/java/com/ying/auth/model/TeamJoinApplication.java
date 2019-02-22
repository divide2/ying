package com.ying.auth.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2019/2/17
 */
@Data
@Entity
@Table(name = "t_team_application")
public class TeamJoinApplication {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String toTeamId;

    private String memoName;

    private Integer fromId;

    private String status;


    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
