package com.divide2.basis.service;

import com.divide2.basis.model.Comment;
import com.divide2.core.basic.service.BasicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public interface CommentService extends BasicService<Comment, Integer> {

    /**
     * 获取某个对象下的所有评论
     *
     * @param toId toid
     * @param type 类型
     * @param pageable pageable
     * @return page
     */
    Page<Comment> findByToIdAndType(Integer toId, String type, Pageable pageable);

    /**
     * 获取用户的评论
     * @param pageable page
     * @return page
     */
    Page<Comment> findByUser(Pageable pageable);
}
