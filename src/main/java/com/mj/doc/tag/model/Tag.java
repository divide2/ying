package com.mj.doc.tag.model;

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

}
