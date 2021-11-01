package com.example.BoardProject.service;

import java.util.List;

import com.example.BoardProject.domain.CommentDto;

public interface CommentService {

    public boolean registerComment(CommentDto commentDto);

    public boolean deleteComment(Long id);

    public List<CommentDto> getCommentList(CommentDto commentDto);

}
