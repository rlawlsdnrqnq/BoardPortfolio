package com.example.BoardProject.mapper;

import com.example.BoardProject.domain.BoardDto;
import com.example.BoardProject.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardMapper {

    public int insertBoard(BoardDto boardDto);

    public BoardDto selectBoardDetail(Long id);

    public int updateBoard(BoardDto boardDto);

    public int deleteBoard(Long id);

    public List<BoardDto> selectBoardList(Criteria criteria);

    public int selectBoardTotalCount(Criteria criteria);

    public void deleteTestData();

}
