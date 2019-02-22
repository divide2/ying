package com.ying.team.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 合作申请
 * @author bvvy
 * @date 2019/2/20
 */
@Data
@Entity
@Table(name = "t_team_cooperation_application")
public class TeamCooperationApplication {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String fromTeamId;

    private String toTeamId;

    private String remarks;

    private String status;

    private LocalDateTime createTime;
}
