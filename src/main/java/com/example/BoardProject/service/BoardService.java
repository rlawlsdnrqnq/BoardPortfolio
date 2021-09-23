package com.example.BoardProject.service;

import com.example.BoardProject.domain.BoardDto;

import java.util.List;

public interface BoardService {
    public boolean registerBoard(BoardDto boardDto);

    public BoardDto getBoardDetail(Long id);

    public boolean deleteBoard(Long id);

    public List<BoardDto> getBoardList();
}
