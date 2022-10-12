package com.springrestone.controller;

import com.springrestone.service.PostService;
import com.springrestone.ultil.request.PostDTO;
import com.springrestone.ultil.response.PostResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/findAll")
    public ResponseEntity<List<PostResponseDTO>> findAll(){
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<PostResponseDTO> create(@RequestBody PostDTO dto){
        return new ResponseEntity<>(postService.save(dto),HttpStatus.CREATED);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable Long id){
        Optional<PostResponseDTO> postResponseDTO = postService.findById(id);
        return new ResponseEntity<>(postResponseDTO.get(),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PostResponseDTO> update(@RequestBody PostDTO dto,@PathVariable Long id){
        Optional<PostResponseDTO> postResponseDTO = postService.findById(id);
        return new ResponseEntity<>(postService.update(dto,id),HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<PostResponseDTO> delete(@PathVariable Long id){
        Optional<PostResponseDTO> postResponseDTO = postService.findById(id);
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
