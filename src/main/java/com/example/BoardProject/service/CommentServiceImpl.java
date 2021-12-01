package com.example.BoardProject.service;

import com.example.BoardProject.domain.CommentDto;
import com.example.BoardProject.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public boolean registerComment(CommentDto commentDto) {
        int queryResult = 0;

        if (commentDto.getId() == null) {
            queryResult = commentMapper.insertComment(commentDto);
        } else {
            queryResult = commentMapper.updateComment(commentDto);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public boolean deleteComment(Long id) {
        int queryResult = 0;

        CommentDto comment = commentMapper.selectCommentDetail(id);

        if (comment != null && "N".equals(comment.getDeleteYn())) {
            queryResult = commentMapper.deleteComment(id);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<CommentDto> getCommentList(CommentDto commentDto) {
        List<CommentDto> commentList = Collections.emptyList();

        int commentTotalCount = commentMapper.selectCommentTotalCount(commentDto);
        if (commentTotalCount > 0) {
            commentList = commentMapper.selectCommentList(commentDto);
        }

        return commentList;
    }
}
