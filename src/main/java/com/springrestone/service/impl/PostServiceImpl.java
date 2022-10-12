package com.springrestone.service.impl;

import com.springrestone.entity.Comment;
import com.springrestone.entity.Post;
import com.springrestone.errors.ResourceNotFoundException;
import com.springrestone.repository.PostRepository;
import com.springrestone.service.PostService;
import com.springrestone.ultil.request.CommentDTO;
import com.springrestone.ultil.request.PostDTO;
import com.springrestone.ultil.response.PostResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;


    @Override
    public List<PostResponseDTO> getAll() {
        return postRepository.findAll().stream().map(p->mapperToPostResponseDTO(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<PostResponseDTO> findById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent()){
            throw new ResourceNotFoundException("Post not found with id: "+id);
        }
        return Optional.of(mapperToPostResponseDTO(postRepository.findById(id).get()));
    }

    @Override
    public PostResponseDTO save(PostDTO dto) {
        Post post = new Post();
        BeanUtils.copyProperties(dto,post);
        Post saved = postRepository.save(post);
        return mapperToPostResponseDTO(saved);
    }

    @Override
    public PostResponseDTO update(PostDTO dto, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("post is not found!"));
        BeanUtils.copyProperties(dto,post);
        Post saved = postRepository.save(post);

        return mapperToPostResponseDTO(saved);
    }

    @Override
    public String delete(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent()){
            throw new ResourceNotFoundException("Post not found with id: "+id);
        }
        postRepository.deleteById(id);
        return "Delete successfully";
    }
    private PostResponseDTO mapperToPostResponseDTO(Post post){
        PostResponseDTO dto = new PostResponseDTO();
        BeanUtils.copyProperties(post,dto);
        if (post.getComments()==null){
            return dto;
        }
        dto.setComments(post.getComments()
                .stream()
                .map(comment -> CommentServiceImpl.mapperToCommentResponseDTO(comment)).collect(Collectors.toSet()));
        return dto;
    }
}
