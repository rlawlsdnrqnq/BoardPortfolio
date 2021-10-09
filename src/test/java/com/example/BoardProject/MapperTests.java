package com.example.BoardProject;

import com.example.BoardProject.domain.BoardDto;
import com.example.BoardProject.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MapperTests {

    @Autowired
    private BoardMapper boardMapper;

    //test 데이터 지울때만 쓸것
    /*@AfterEach
    public void afterEach() {
        boardMapper.deleteTestData();
    }*/

    @Test
    public void insertBoard() {
        //given
        BoardDto boardDTO = new BoardDto();
        boardDTO.setTitle("1번 게시글 제목");
        boardDTO.setContent("1번 게시글 내용");
        boardDTO.setAuthor("테스터");

        //when
        int result = boardMapper.insertBoard(boardDTO);

        //then
        assertThat(1).isEqualTo(result);
    }

    //기본적으로 쿼리가 실행되면 쿼리 실행 횟수에 해당하는 1이 포함된다.
    //Java 8 date/time type 오류 해결: jackson라이브러리 추가 , registerModule(new JavaTimeModule() 추가
    @Test
    public void SelectBoardDetail() {
        BoardDto board = boardMapper.selectBoardDetail((long) 1);
        try {
            String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

            System.out.println("=========================");
            System.out.println(boardJson);
            System.out.println("=========================");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateBoard() {
        BoardDto boardDTO = new BoardDto();
        boardDTO.setId((long) 1);
        boardDTO.setAuthor("홍길동");
        boardDTO.setContent("1번게시물 내용을 수정합니다");
        boardDTO.setTitle("1번 게시물 제목을 수정합니다");

        int result = boardMapper.updateBoard(boardDTO);
        if(result == 1) {
            try {
                BoardDto board = boardMapper.selectBoardDetail((long) 1);
                String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

                System.out.println("==========");
                System.out.println(boardJson);
                System.out.println("==========");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void deleteBoard() {

        int result = boardMapper.deleteBoard((long) 51);
        if (result == 51) {
            try {
                BoardDto board = boardMapper.selectBoardDetail((long) 51);
                String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

                System.out.println("=========");
                System.out.println(boardJson);
                System.out.println("=========");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
        @Test
        public void multipleInsert() {
            for(int i = 2; i <= 50; i++) {
                BoardDto boardDTO = new BoardDto();
                boardDTO.setTitle(i + "번 게시글 제목");
                boardDTO.setContent(i + "번 게시글 내용");
                boardDTO.setAuthor(i + "번 게시글 작성자");
                boardMapper.insertBoard(boardDTO);
            }
        }

        @Test
        public void selectBoardList(BoardDto boardDto) {
            int boardTotalCount = boardMapper.selectBoardTotalCount(boardDto);
            List<BoardDto> boardList = boardMapper.selectBoardList(boardDto);

            if(boardTotalCount > 0) {
                if(!CollectionUtils.isEmpty(boardList)) {
                    for(BoardDto board : boardList) {
                        System.out.println("===========");
                        System.out.println(board.getTitle());
                        System.out.println(board.getAuthor());
                        System.out.println(board.getContent());
                        System.out.println("===========");
                    }
                }
            }
        }
    }

