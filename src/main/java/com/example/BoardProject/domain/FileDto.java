package com.example.BoardProject.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto extends CommonDto{

    /** 파일 번호 (PK) */
    private Long id;

    /** 게시글 번호 (FK) */
    private Long boardId;

    /** 원본 파일명 */
    private String originalName;

    /** 저장 파일명 */
    private String saveName;

    /** 파일 크기 */
    private long size;
}
