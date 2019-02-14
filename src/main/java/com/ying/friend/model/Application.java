package com.ying.friend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 申请
 * @author bvvy
 * @date 2019/2/14
 */
@Entity
@Table(name = "f_application")
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer fromId;

    private Integer toId;

    private String memoName;

    private String remarks;

    private String status;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;


}
