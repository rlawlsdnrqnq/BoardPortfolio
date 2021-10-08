package com.example.BoardProject.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto extends CommonDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    private int viewCnt;
    private String noticeYn;
    private String secretYn;

}