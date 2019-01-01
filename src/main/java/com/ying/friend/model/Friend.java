package com.ying.friend.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/12/3
 */
@Entity
@Table(name="f_friend")
@Data
public class Friend {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private Integer fromId;
    private Integer toId;
    /**
     * 备注名
     */
    private String memoName;
    /**
     * 加好友时间
     */
    private LocalDateTime createTime;
}
