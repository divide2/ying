package com.ying.doc.article.vo;

import com.ying.core.val.Punctuation;
import com.ying.doc.article.model.Article;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleVO {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;
    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;
    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;
    /**
     * 标签
     */
    @ApiModelProperty("标签")
    private List<String> tags;

    /**
     * 创建人id
     */
    @ApiModelProperty("创建人id")
    private Integer authorId;

    /**
     * 创建人名称
     */
    @ApiModelProperty("创建人名称")
    private String author;

    /**
     * 更新的人
     */
    @ApiModelProperty("更新的人")
    private Integer editorId;

    /**
     * 更新ren 名称
     */
    @ApiModelProperty("更新人名称")
    private String editor;
    /**
     * 截止日期
     */
    @ApiModelProperty("截止日期")
    private LocalDateTime expiry;
    /**
     * 更新日期
     */
    @ApiModelProperty("更新日期")
    private LocalDateTime updatedDate;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createdDate;

    /**
     * 启用
     */
    @ApiModelProperty("启用")
    private Character enabled;


    public static ArticleVO fromArticle(Article article) {
        return ArticleVO.builder()
                .id(article.getId())
                .content(article.getContent())
                .expiry(article.getExpiry())
                .createdDate(article.getCreatedDate())
                .authorId(article.getCreatedUserId())
                .author(article.getCreatedUsername())
                .enabled(article.getEnabled())
                .tags(Arrays.asList(article.getTags().split(Punctuation.COMMA)))
                .title(article.getTitle())
                .updatedDate(article.getUpdatedDate())
                .editorId(article.getUpdatedUserId())
                .updatedDate(article.getUpdatedDate())
                .editor(article.getUpdatedUsername())
                .build();
    }
}
