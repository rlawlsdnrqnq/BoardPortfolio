package com.example.BoardProject.controller;

import com.example.BoardProject.domain.BoardDto;
import com.example.BoardProject.service.BoardService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping(value = "/board/write.do")
    public String openBoardWrite(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("board", new BoardDto());
        } else {
            BoardDto board = boardService.getBoardDetail(id);
            if (board == null) {
                return "redirect:/board/list.do";
            }
            model.addAttribute("board", board);
        }

        return "board/write";
    }

    @GetMapping(value = "/board/list.do")
    public String openBoardList(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("boardList", boardDtoList);

        return "board/list";
    }

    @PostMapping(value = "/board/register.do")
    public String registerBoard(final BoardDto boardDto) {
        try {
            boolean isRegistered = boardService.registerBoard(boardDto);
            if (isRegistered == false) {
                // TODO => 게시글 등록에 실패하였다는 메시지를 전달
            }
        } catch (DataAccessException e) {
            // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

        } catch (Exception e) {
            // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
        }

        return "redirect:/board/list.do";
    }

    @GetMapping(value = "/board/view.do")
    public String openBoardDetail(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id == null) {
            // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/board/list.do";
        }

        BoardDto board = boardService.getBoardDetail(id);
        if (board == null || "Y".equals(board.getDeleteYn())) {
            // TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/board/list.do";
        }
        model.addAttribute("board", board);

        return "board/view";
    }

    @PostMapping(value = "/board/delete.do")
    public String deleteBoard(@RequestParam(value = "id", required = false) Long id) {
        if(id == null) {
            //TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/board/list.do";
    }
    try{
        boolean isDeleted = boardService.deleteBoard(id);
        if(isDeleted == false) {
            //TODO => 게시글을 삭제에 실패하였다는 메시지를 전달
        }
    } catch (DataAccessException e) {
        //TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
    } catch (Exception e) {
        //TODO => 시스템에 문제가 발생하였다는 메시지를 전달
    }
    return "redirect:/board/list.do";
    }
}
