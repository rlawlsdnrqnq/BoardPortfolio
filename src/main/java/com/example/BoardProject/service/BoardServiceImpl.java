package com.example.BoardProject.service;

import com.example.BoardProject.domain.BoardDto;
import com.example.BoardProject.mapper.BoardMapper;
import com.example.BoardProject.paging.Criteria;
import com.example.BoardProject.paging.PaginationInfo;
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

        BoardDto board = boardMapper.selectBoardDetail(id);

        if (board != null && "N".equals(board.getDeleteYn())) {
            queryResult = boardMapper.deleteBoard(id);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<BoardDto> getBoardList(Criteria criteria) {
        List<BoardDto> boardDtoList = Collections.emptyList();
        int boardTotalCount = boardMapper.selectBoardTotalCount(criteria);

        if (boardTotalCount > 0) {
            boardDtoList = boardMapper.selectBoardList(criteria);
        }
        return boardDtoList;
    }
}
