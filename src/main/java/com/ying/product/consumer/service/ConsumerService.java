package com.ying.product.consumer.service;

import com.ying.product.product.dto.ProductCommentAddDTO;

/**
 * @author bvvy
 * @date 2018/9/29
 */
public interface ConsumerService {


    /**
     * 添加产品评论
     * @param comment comment
     */
    void addComment(ProductCommentAddDTO comment);
}
