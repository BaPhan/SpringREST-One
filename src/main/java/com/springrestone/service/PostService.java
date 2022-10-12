package com.springrestone.service;

import com.springrestone.ultil.request.PostDTO;
import com.springrestone.ultil.response.PostResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public List<PostResponseDTO> getAll();
    public Optional<PostResponseDTO> findById(Long id);
    public PostResponseDTO save(PostDTO dto);
    public PostResponseDTO update(PostDTO dto,Long id);
    public String delete(Long id);

}
