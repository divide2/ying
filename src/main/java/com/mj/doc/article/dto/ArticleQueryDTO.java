package com.mj.doc.article.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@Data
public class ArticleQueryDTO {

    private String keyword;
    private String tag;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
