package com.ying.basis.comment.repo;

import com.ying.basis.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/7/19
 */
public interface CommentRepository extends JpaRepository<Comment,Integer> {


}
