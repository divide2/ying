package com.mj.doc.article.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 文章
 * @author bvvy
 * @date 2018/7/19
 */
@Entity
@Table(name = "doc_article")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    @Column(columnDefinition="TEXT")
    private String content;
    /**
     * 标签
     */
    private String tags;

    /**
     * 创建人id
     */
    @Column(name = "created_user_id")
    private Integer createdUserId;

    /**
     * 创建人名称
     */
    @Column(name = "created_username")
    private String createdUsername;

    /**
     * 更新的人
     */
    @Column(name = "updated_user_id")
    private Integer updatedUserId;

    /**
     * 更新ren 名称
     */
    @Column(name = "updated_username")
    private String updatedUsername;
    /**
     * 截止日期
     */
    private LocalDateTime expiry;
    /**
     * 更新日期
     */
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    /**
     * 创建时间
     */
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    /**
     * 启用
     */
    private Character enabled;

    /**
     * 删除
     */
    private Character deleted;

    /**
     * 发表状态 /发表为草稿 还是 文章
     * draft publish
     */
    @Column(name = "publish_status")
    private String publishStatus;


}
