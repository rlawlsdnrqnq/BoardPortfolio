package com.example.BoardProject.controller;

import com.example.BoardProject.constant.Method;
import com.example.BoardProject.domain.BoardDto;
import com.example.BoardProject.domain.FileDto;
import com.example.BoardProject.paging.Criteria;
import com.example.BoardProject.service.BoardService;
import com.example.BoardProject.util.UiUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class BoardController extends UiUtils {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping(value = "/board/write.do")
    public String openBoardWrite(@ModelAttribute("boardDto") BoardDto boardDto, @RequestParam(value = "id", required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("board", new BoardDto());
        } else {
            BoardDto board = boardService.getBoardDetail(id);
            if (board == null || "Y".equals(board.getDeleteYn())) {
                return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list.do", Method.GET, null, model);
            }
            model.addAttribute("board", board);

            List<FileDto> fileList = boardService.getFileList(id);
            model.addAttribute("fileList", fileList);
        }

        return "board/write";
    }

    @GetMapping(value = "/board/list.do")
    public String openBoardList(@ModelAttribute("boardDto")BoardDto boardDto, Model model) {
        List<BoardDto> boardList = boardService.getBoardList(boardDto);
        model.addAttribute("boardList", boardList);

        return "board/list";
    }

    @PostMapping(value = "/board/register.do")
    public String registerBoard(final BoardDto boardDto, final MultipartFile[] files, Model model) {
        Map<String, Object> pagingParams = getPagingParams(boardDto);
        try {
            boolean isRegistered = boardService.registerFile(boardDto, files);
            if (isRegistered == false) {
                return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);

        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
        }

        return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/board/list.do", Method.GET, pagingParams, model);
    }

    @GetMapping(value = "/board/view.do")
    public String openBoardDetail(@ModelAttribute("boardDto") BoardDto boardDto, @RequestParam(value = "id", required = false) Long id, Model model) {
        if (id == null) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
        }

        BoardDto board = boardService.getBoardDetail(id);
        if (board == null || "Y".equals(board.getDeleteYn())) {
            return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list.do", Method.GET, null, model);
        }
        model.addAttribute("board", board);

        return "board/view";
    }

    @PostMapping(value = "/board/delete.do")
    public String deleteBoard(@ModelAttribute("boardDto") BoardDto boardDto, @RequestParam(value = "id", required = false) Long id, Model model) {
        if(id == null) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
    }
    Map<String, Object> pagingParams = getPagingParams(boardDto);
    try{
        boolean isDeleted = boardService.deleteBoard(id);
        if(!isDeleted) {
            return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
        }
    } catch (DataAccessException e) {
        return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
    } catch (Exception e) {
        return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
    }
    return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/board/list.do", Method.GET, pagingParams, model);
    }
}
