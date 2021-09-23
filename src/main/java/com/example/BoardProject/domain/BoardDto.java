package com.example.BoardProject.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    private int viewCnt;
    private String noticeYn;
    private String secretYn;
    private String deleteYn;

    private LocalDateTime insertTime;
    private LocalDateTime updateTime;
    private LocalDateTime deleteTime;


}