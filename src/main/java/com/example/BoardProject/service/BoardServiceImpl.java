package com.example.BoardProject.service;

import com.example.BoardProject.domain.BoardDto;
import com.example.BoardProject.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public boolean registerBoard(BoardDto boardDto) {
        int queryResult = 0;

        if (boardDto.getId() == null) {
            queryResult = boardMapper.insertBoard(boardDto);
        } else {
            queryResult = boardMapper.updateBoard(boardDto);
        }
        return (queryResult == 1) ? true : false;
    }

    @Override
    public BoardDto getBoardDetail(Long id) {
        return boardMapper.selectBoardDetail(id);
    }

    @Override
    public boolean deleteBoard(Long id) {
        int queryResult = 0;
        BoardDto boardDto = boardMapper.selectBoardDetail(id);

        if (boardDto.getId() != null && "N".equals(boardDto.getDeleteYn())) {
            queryResult = boardMapper.deleteBoard(id);
        }
        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<BoardDto> getBoardList() {
        List<BoardDto> boardDtoList = Collections.emptyList();
        int boardTotalCount = boardMapper.selectBoardTotalCount();

        if (boardTotalCount > 0) {
            boardDtoList = boardMapper.selectBoardList();
        }
        return boardDtoList;
    }
}
