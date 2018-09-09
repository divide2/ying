package com.ying.basis.star.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/9/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "basis_star")
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 点赞的人
     */
    private String fromId;
    private String fromName;
    private String fromAvatar;
    /**
     * 被点赞的对象
     */
    private Integer toId;

    /**
     * 被点赞的对象的名称
     */
    private String toName;
    /**
     * 创建时间
     */
    private LocalDateTime cdt;
}
