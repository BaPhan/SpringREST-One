package com.springrestone.service;

import com.springrestone.dto.CommentDTO;
import com.springrestone.entity.Comment;
import com.springrestone.repository.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(CommentDTO commentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(Long aLong) {
        return commentRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return commentRepository.existsById(aLong);
    }

    @Override
    public long count() {
        return commentRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
