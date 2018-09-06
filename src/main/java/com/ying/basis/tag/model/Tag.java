package com.ying.basis.tag.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 标签
 * @author bvvy
 * @date 2018/7/19
 */
@Entity
@Table(name = "doc_tag")
@Data
public class Tag {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * name
     */
    private String name;
    /**
     * 使用次数
     */
    private Integer counter;

    /**
     * 标签的类型 用于那一个对象的标签
     */
    private String type;

    /**
     * 添加的人
     */
    private String fromId;

    /**
     * 添加人的用户名
     */
    private String fromName;


}
