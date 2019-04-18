package com.divide2.basis.service.impl;

import com.divide2.basis.model.Comment;
import com.divide2.basis.repo.CommentRepository;
import com.divide2.basis.service.CommentService;
import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import com.divide2.core.er.Loginer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@Service
public class CommentServiceImpl extends SimpleBasicServiceImpl<Comment, Integer, CommentRepository> implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public Page<Comment> findByToIdAndType(Integer toId, String type, Pageable pageable) {
        return commentRepository.findByToIdAndType(toId, type, pageable);
    }

    @Override
    public Page<Comment> findByUser(Pageable pageable) {
        return  commentRepository.findByFromId(Loginer.userId(), pageable);
    }

}
