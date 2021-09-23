package com.example.BoardProject.mapper;

import java.util.List;

import com.example.BoardProject.domain.BoardDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BoardMapper {

    public int insertBoard(BoardDto boardDto);

    public BoardDto selectBoardDetail(Long id);

    public int updateBoard(BoardDto boardDto);

    public int deleteBoard(Long id);

    public List<BoardDto> selectBoardList();

    public int selectBoardTotalCount();

    public void deleteTestData();

}
