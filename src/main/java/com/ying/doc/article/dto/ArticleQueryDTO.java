package com.ying.doc.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@Data
@ApiModel("文章查询")
public class ArticleQueryDTO {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("tag")
    private String tag;

    @ApiModelProperty("日期")
    private List<LocalDateTime> publishDates;
}
