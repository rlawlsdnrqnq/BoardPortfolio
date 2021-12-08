package com.example.BoardProject.mapper;

import com.example.BoardProject.domain.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardMapper {

    public int insertBoard(BoardDto boardDto);

    public BoardDto selectBoardDetail(Long id);

    public int updateBoard(BoardDto boardDto);

    public int deleteBoard(Long id);

    public List<BoardDto> selectBoardList(BoardDto boardDto);

    public int selectBoardTotalCount(BoardDto boardDto);

    //public void deleteTestData();

}
