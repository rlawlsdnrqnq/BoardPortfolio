package com.example.BoardProject.mapper;

import com.example.BoardProject.domain.FileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    public int insertFile(List<FileDto> fileDtoList);

    public FileDto selectFileDetail(Long id);

    public int deleteFile(Long boardId);

    public List<FileDto> selectFileList(Long boardId);

    public int selectFileTotalCount(Long boardId);

    public int undeleteFile(List<Long> ids);
}
