package com.ying.attention.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2019/1/4
 */
@Table(name = "a_attention")
@Entity
@Data
public class Attention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime createTime;
    private String title;
    private String content;
    /**
     * 用 模板？
     */
    private String type;
}
