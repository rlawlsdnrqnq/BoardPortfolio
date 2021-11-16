package com.example.BoardProject.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto extends CommonDto{

    private Long id;
    private Long boardId;
    private String originalName;
    private String saveName;
    private long size;
}
