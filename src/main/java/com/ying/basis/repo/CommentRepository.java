package com.ying.basis.repo;

import com.ying.basis.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {


    /**
     * 获取某个对象下面的所有的评论
     *
     * @param toId     对象id
     * @param type     类型
     * @param pageable page
     * @return page
     */
    Page<Comment> findByToIdAndType(Integer toId, String type, Pageable pageable);

    /**
     * 获取我的评论
     *
     * @param userId   userId
     * @param pageable page
     * @return page
     */
    Page<Comment> findByFromId(Integer userId, Pageable pageable);
}
