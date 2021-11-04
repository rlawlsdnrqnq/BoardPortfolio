package com.example.BoardProject.controller;

import com.example.BoardProject.adapter.GsonLocalTimeAdapter;
import com.example.BoardProject.domain.CommentDto;
import com.example.BoardProject.service.CommentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/comments/{boardId}")
    public JsonObject getCommentList(@PathVariable("boardId") Long boardId, @ModelAttribute("params") CommentDto params) {

        JsonObject jsonObj = new JsonObject();

        List<CommentDto> commentList = commentService.getCommentList(params);
        if (CollectionUtils.isEmpty(commentList) == false) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalTimeAdapter()).create();
            JsonArray jsonArr = gson.toJsonTree(commentList).getAsJsonArray();
            jsonObj.add("commentList", jsonArr);
        }
        return jsonObj;
    }
}
