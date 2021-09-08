package com.example.BoardProject.mapper;

import com.example.BoardProject.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public int insertBoard(BoardDto boardDto);
    public BoardDto selectBoardDetail(Long idx);
    public int updateBoard(BoardDto boardDto);
    public int deleteBoard(Long idx);
    public List<BoardDto> selectBoardList();
    public int selectBoardTotalCount();

}
