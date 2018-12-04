package com.ying.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/8/19
 */
@Data
@Entity
@Table(name = "m_message")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 发的人
     */
    private Integer fromId;
    /**
     * 发的人
     */
    private String fromName;
    /**
     * 发的人头像
     */
    private String fromAvatar;
    /**
     * 给的人
     */
    private Integer toId;
    /**
     * 给的人名称
     */
    private String toName;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 创建时间
     */
    private LocalDateTime cdt;
    /**
     * 读过么
     */
    private Boolean readed;
}

