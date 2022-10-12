package com.springrestone.ultil.response;

import com.springrestone.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Integer maximumOfContent;
    private Set<CommentResponseDTO> comments;

}
