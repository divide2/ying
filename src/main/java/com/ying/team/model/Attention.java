package com.ying.team.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String teamId;
    private LocalDateTime createTime;
    private String title;
    private String content;
    /**
     * 用 模板？
     */
    private String type;
}
