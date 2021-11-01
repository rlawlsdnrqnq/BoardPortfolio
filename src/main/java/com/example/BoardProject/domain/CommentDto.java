package com.example.BoardProject.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto extends CommonDto{

    private Long id;
    private Long boardId;
    private String content;
    private String author;

}
