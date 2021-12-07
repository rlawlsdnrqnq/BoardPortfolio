package com.example.BoardProject.service;

import com.example.BoardProject.domain.BoardDto;
import com.example.BoardProject.domain.FileDto;
import com.example.BoardProject.mapper.BoardMapper;
import com.example.BoardProject.mapper.FileMapper;
import com.example.BoardProject.paging.Criteria;
import com.example.BoardProject.paging.PaginationInfo;
import com.example.BoardProject.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    private final FileMapper fileMapper;

    private final FileUtils fileUtils;

    public BoardServiceImpl(BoardMapper boardMapper, FileMapper fileMapper, FileUtils fileUtils) {
        this.boardMapper = boardMapper;
        this.fileMapper = fileMapper;
        this.fileUtils = fileUtils;
    }

    @Override
    public boolean registerBoard(BoardDto boardDto) {
        int queryResult = 0;

        if (boardDto.getId() == null) {
            queryResult = boardMapper.insertBoard(boardDto);
        } else {
            queryResult = boardMapper.updateBoard(boardDto);

            //파일이 추가, 삭제, 변경된 경우
            if("Y".equals(boardDto.getChangeYn())) {
                fileMapper.deleteFile(boardDto.getId());

                    //fileIds에 포함된 id를 가지는 파일의 삭제 여부를 'N'으로 업데이트
                    if(!CollectionUtils.isEmpty(boardDto.getFileIds())) {
                        fileMapper.undeleteFile(boardDto.getFileIds());
                    }
                }
        }
        return (queryResult > 0);
    }

    @Override
    public boolean registerFile(BoardDto boardDto, MultipartFile[] files) {
        int queryResult = 1;

        if(!registerBoard(boardDto)) {
            return false;
        }

        List<FileDto> fileDtoList = fileUtils.uploadFiles(files, boardDto.getId());
        if(CollectionUtils.isEmpty(fileDtoList) == false) {
            queryResult = fileMapper.insertFile(fileDtoList);
            if(queryResult < 1) {
                queryResult = 0;
            }
        }
        return (queryResult > 0);
    }

    @Override
    public BoardDto getBoardDetail(Long id) {
        return boardMapper.selectBoardDetail(id);
    }

    @Override
    public boolean deleteBoard(Long id) {
        int queryResult = 0;

        BoardDto board = boardMapper.selectBoardDetail(id);

        if (board != null && "N".equals(board.getDeleteYn())) {
            queryResult = boardMapper.deleteBoard(id);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<BoardDto> getBoardList(BoardDto boardDto) {
        List<BoardDto> boardList = Collections.emptyList();

        int boardTotalCount = boardMapper.selectBoardTotalCount(boardDto);

        PaginationInfo paginationInfo = new PaginationInfo(boardDto);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        boardDto.setPaginationInfo(paginationInfo);

        if (boardTotalCount > 0) {
            boardList = boardMapper.selectBoardList(boardDto);
        }
        return boardList;
    }

    @Override
    public List<FileDto> getFileList(Long boardId) {
        int fileTotalCount = fileMapper.selectFileTotalCount(boardId);
        if(fileTotalCount < 1) {
            return Collections.emptyList();
        }
        return fileMapper.selectFileList(boardId);
    }

    @Override
    public FileDto getFileDetail(Long id) {
        return fileMapper.selectFileDetail(id);

    }
}
