package com.example.BoardProject.service;

import com.example.BoardProject.domain.CommentDto;

import java.util.List;

public interface CommentService {
    public boolean registerComment(CommentDto commentDto);

    public boolean deleteComment(Long id);

    public List<CommentDto> getCommentList(CommentDto commentDto);
}
