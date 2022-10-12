package com.springrestone.service;

import com.springrestone.entity.Comment;
import com.springrestone.ultil.request.CommentDTO;
import com.springrestone.ultil.request.PostDTO;
import com.springrestone.ultil.response.CommentResponseDTO;
import com.springrestone.ultil.response.PostResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    public List<CommentResponseDTO> getAll();
    public Optional<CommentResponseDTO> findById(Long id);
    public CommentResponseDTO save(CommentDTO dto);
    public CommentResponseDTO update(CommentDTO dto,Long id);
    public String delete(Long id);;
}
