package com.example.BoardProject.service;

import com.example.BoardProject.domain.BoardDto;
import com.example.BoardProject.domain.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    public boolean registerBoard(BoardDto boardDto);

    public boolean registerBoard(BoardDto boardDto, MultipartFile[] files);

    public BoardDto getBoardDetail(Long id);

    public boolean deleteBoard(Long id);

    public List<BoardDto> getBoardList(BoardDto boardDto);

    public List<FileDto> getFileList(Long boardId);
}
