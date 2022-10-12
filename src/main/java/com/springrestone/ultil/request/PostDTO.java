package com.springrestone.ultil.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    @NotNull(message = "title is not requrire")
    @NotEmpty(message = "field is not empty")
    private String title;
    @NotNull(message = "description is not required")
    @NotEmpty(message = "description is not empty")
    private String description;
    private String content;
//    private Set<CommentDTO> comments;
}
