package com.example.BoardProject.domain;

import com.example.BoardProject.paging.Criteria;
import com.example.BoardProject.paging.PaginationInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class CommonDto extends Criteria {

    private PaginationInfo paginationInfo;
    private String deleteYn;
    private LocalDateTime insertTime;
    private LocalDateTime updateTime;
    private LocalDateTime deleteTime;
}
