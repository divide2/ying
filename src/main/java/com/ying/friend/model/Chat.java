package com.ying.friend.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 聊天 就是最近聊过的人
 *
 * @author bvvy
 * @date 2018/12/28
 */
@Entity
@Table(name = "f_chat")
@Data
public class Chat {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private Integer fromId;
    private Integer toId;
    /**
     * 最后一条消息
     */
    private String lastMessage;
    /**
     * 最后一条消息的时间
     */
    private LocalDateTime lastTime;
    /**
     * 没有读过的数量
     */
    private Integer unreadCount;
    /**
     * 排序号
     */
    private Integer orderNum;
}
