package com.example.BoardProject.mapper;

import java.util.List;

import com.example.BoardProject.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BoardMapper {

    public int insertBoard(BoardDTO params);

    public BoardDTO selectBoardDetail(Long idx);

    public int updateBoard(BoardDTO params);

    public int deleteBoard(Long idx);

    public List<BoardDTO> selectBoardList();

    public int selectBoardTotalCount();

}
