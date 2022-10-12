package com.springrestone.ultil.request;

import com.springrestone.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String name;
    private String email;
    private String body;
    //private Post post;
    private Long postId;
}

