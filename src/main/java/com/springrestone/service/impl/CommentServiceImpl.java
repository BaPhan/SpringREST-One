package com.springrestone.service.impl;

import com.springrestone.entity.Comment;
import com.springrestone.entity.Post;
import com.springrestone.errors.ResourceNotFoundException;
import com.springrestone.repository.CommentRepository;
import com.springrestone.repository.PostRepository;
import com.springrestone.service.CommentService;
import com.springrestone.ultil.request.CommentDTO;
import com.springrestone.ultil.request.PostDTO;
import com.springrestone.ultil.response.CommentResponseDTO;
import com.springrestone.ultil.response.PostResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;


    @Override
    public List<CommentResponseDTO> getAll() {
        return commentRepository.findAll().stream().map(comment -> mapperToCommentResponseDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentResponseDTO> findById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (!comment.isPresent()){
            throw new ResourceNotFoundException("Comment not found with id: "+id);
        }
        return Optional.of(mapperToCommentResponseDTO(commentRepository.findById(id).get()));
    }

    @Override
    public CommentResponseDTO save(CommentDTO dto) {
        Post p = postRepository.findById(dto.getPostId())
                .orElseThrow(()-> new ResourceNotFoundException("post not found to get"));
        Comment comment = new Comment();
        BeanUtils.copyProperties(dto,comment);
        comment.setPost(p);
        Comment saved = commentRepository.save(comment);
        return mapperToCommentResponseDTO(saved);
    }

    @Override
    public CommentResponseDTO update(CommentDTO dto, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("comment not found !"));
        Post p = postRepository.findById(dto.getPostId())
                .orElseThrow(()-> new ResourceNotFoundException("post not found to get"));
        BeanUtils.copyProperties(dto,comment);
        comment.setPost(p);
        Comment saved = commentRepository.save(comment);
        return mapperToCommentResponseDTO(saved);
    }

    @Override
    public String delete(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (!comment.isPresent()){
            throw new ResourceNotFoundException("comment not found!");
        }
        commentRepository.deleteById(id);
        return "Delete successfully";
    }
    public  static CommentResponseDTO mapperToCommentResponseDTO(Comment comment){
        CommentResponseDTO dto = new CommentResponseDTO();
        BeanUtils.copyProperties(comment,dto);
        dto.setPosttitle(comment.getPost().getTitle());
        return dto;
    }
}
