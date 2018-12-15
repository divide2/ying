package com.ying.basis.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * todo not hear
 * 标签
 *
 * @author bvvy
 * @date 2018/7/19
 */
@Entity
@Table(name = "basis_comment")
@Data
public class Comment {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 内容
     */
    private String content;
    /**
     * 标签的类型 用于那一个对象的评论 评论用户 / 评论作品 /评论其他
     */
    private String type;

    /**
     * 对应某个对象的id
     */
    private Integer toId;
    /**
     * 添加的人
     */
    private Integer fromId;

    /**
     * 添加的人名
     */
    private String fromName;

    /**
     * 头像
     */
    private String fromAvatar;

    /**
     * 创建时间
     */
    private LocalDateTime cdt;


}
