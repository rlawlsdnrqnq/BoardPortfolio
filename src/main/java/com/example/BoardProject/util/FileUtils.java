package com.example.BoardProject.util;

import com.example.BoardProject.domain.FileDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {
    /*오늘 날짜*/
    private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

    /*업로드 경로*/
    private final String uploadPath = Paths.get("E:", "develop", "upload", today).toString();

    /**
     * 서버에 생성할 파일명을 처리하는 랜덤 문자열 반환
     * @return 랜덤 문자열
    */
    private final String getRandowString() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 서버에 첨부파일을 생성, 업로드 파일 목록 반환
     * @param files   -파일 Array
     * @param boardId -게시글 번호
     * @return 업로드 파일 목록
     */
    public List<FileDto> uploadFiles(MultipartFile[] files, Long boardId) {
        /* 파일이 비어있으면 비어있는 리스트 반환*/
        if(files[0].getSize() < 1) {
            return Collections.emptyList();
        }
        /* 업로드 파일 정보를 담을 비어있는 리스트 */
        List<FileDto> fileDtoList = new ArrayList<>();

        /* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리리를 생성 */
        File dir = new File(uploadPath);
        if(dir.exists() == false) {
            dir.mkdirs();
        }
        /* 파일 개수만큼 forEach 실행 */
        for(MultipartFile file : files) {
            try {
                /* 파일 확장자 */
                final String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            }
        }
    }

