package com.springrestone.controller;
import com.springrestone.service.CommentService;
import com.springrestone.ultil.request.CommentDTO;
import com.springrestone.ultil.response.CommentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/findAll")
    public ResponseEntity<List<CommentResponseDTO>> findAll(){
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<CommentResponseDTO> create(@RequestBody CommentDTO dto){
        return new ResponseEntity<>(commentService.save(dto),HttpStatus.CREATED);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<CommentResponseDTO> findById(@PathVariable Long id){
        Optional<CommentResponseDTO> commentResponseDTO = commentService.findById(id);
        return new ResponseEntity<>(commentResponseDTO.get(),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CommentResponseDTO> update(@RequestBody CommentDTO dto,@PathVariable Long id){
        Optional<CommentResponseDTO> commentResponseDTO = commentService.findById(id);
        return new ResponseEntity<>(commentService.update(dto,id),HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<CommentResponseDTO> delete(@PathVariable Long id){
        Optional<CommentResponseDTO> commentResponseDTO = commentService.findById(id);
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
