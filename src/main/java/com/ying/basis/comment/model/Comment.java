package com.ying.basis.comment.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 标签
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
    private Integer oid;
    /**
     * 添加的人
     */
    private String fromId;


}
