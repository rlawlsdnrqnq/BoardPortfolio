package com.example.BoardProject.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardDto extends CommonDto {

    /** 번호 (PK) */
    private Long id;
    /** 제목 */
    private String title;
    /** 내용 */
    private String content;
    /** 작성자 */
    private String author;

    /** 조회 수 */
    private int viewCnt;
    /** 공지 여부 */
    private String noticeYn;
    /** 비밀 여부 */
    private String secretYn;

    /** 파일 변경 여부 */
    private String changeYn;
    /** 파일 인덱스 리스트 */
    private List<Long> fileIds;

}