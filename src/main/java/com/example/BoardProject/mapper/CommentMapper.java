package com.example.BoardProject.mapper;

import com.example.BoardProject.domain.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    public int insertComment(CommentDto commentDto);

    public CommentDto selectCommentDetail(Long id);

    public int updateComment(CommentDto commentDto);

    public int deleteComment(Long id);

    public List<CommentDto> selectCommentList(CommentDto commentDto);

    public int selectCommentTotalCount(CommentDto commentDto);

}
