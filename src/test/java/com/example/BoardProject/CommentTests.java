/*
package com.example.BoardProject;
import com.example.BoardProject.domain.CommentDto;
import com.example.BoardProject.mapper.CommentMapper;
import com.example.BoardProject.service.CommentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CommentTests {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void registerComment() {
        int queryResult = 0;
        int number = 20;

        for (int i = 1; i <= number; i++) {
            CommentDto params = new CommentDto();
            params.setBoardId((long) 544); // 댓글을 추가할 게시글 번호
            params.setContent(i + "번 댓글을 추가합니다!");
            params.setAuthor(i + "번 회원");
            queryResult = commentMapper.insertComment(params);
        }
        assertThat(1).isEqualTo(queryResult);
    }

    @Test
    public void deleteComment() {
        commentMapper.deleteComment((long) 10); // 삭제할 댓글 번호

        getCommentList();
    }

    @Test
    public void getCommentList() {
        CommentDto params = new CommentDto();
        params.setBoardId((long) 544); // 댓글을 추가할 게시글 번호

        commentMapper.selectCommentList(params);
    }

}*/
