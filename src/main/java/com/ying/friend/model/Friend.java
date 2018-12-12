package com.ying.friend.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author bvvy
 * @date 2018/12/3
 */
@Entity
@Table(name="f_friend")
@Data
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer fromId;
    private Integer toId;
    private String memoName;

}
