package com.springrestone.dto;

import com.springrestone.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String name;
    private String email;
    private String body;
    private Post post;
}

