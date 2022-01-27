# MybatisBoard Project
---
## 목차

1.개발 목적  

2.개발 환경 

3.프로젝트 구조  

4.기능 소개  

5.느낀 점

---
## 개발 목적
* Web 개발자가 되기 위한 기본기 다지기
* MVC 구조 이해
*
---
## 개발 환경
* bootstrap 4.5.0
* thymeleaf 
* Java 11
* MySQL 8.0
* mybatis
* lombok
* Spring Boot 2.5.4
* Gradle
* IntellJ 2021-02
---
## 프로젝트 구조
<img width="300" align="left" src="https://user-images.githubusercontent.com/83013198/147532500-8b19115b-8b2b-4077-b048-565e030aed82.png">
<img width="300" src="https://user-images.githubusercontent.com/83013198/147532503-e9228b67-a430-444c-ad49-e6843d0e692e.png">

<br><br>

<img width="300" align="left" src="https://user-images.githubusercontent.com/83013198/147803248-7edb73e1-7e0a-4451-83ad-97fdfd460931.PNG">
<img width="300" src="https://user-images.githubusercontent.com/83013198/147805420-c83be289-094d-4868-bcb4-3a0b0496e38c.png">

<br><br><br><br><br><br><br><br><br><br><br>
sql 구문을 매핑하는 방법으로는 xml방식으로 적용했습니다. 
<br>
코드가 길어질수록 직관적으로 확인할 수 있기때문입니다.
<br><br>
공통적으로 사용하는 js/css는 언어 통계에서 제외시켰습니다.
<br>
javascript는 .html안에서 사용했기 때문에 통계되지 않습니다.

---
## 기능

<br>
기본적인 CRUD인 글생성, 글읽기, 글수정, 글삭제는 포트폴리오에서 제외했습니다.

---
### 파일 업로드
![업로드](https://user-images.githubusercontent.com/83013198/148327655-2e4697dc-84c9-4a4b-98d4-b4ed19954d06.gif)
업로드 파일은 서버 구동 폴더 밖 외부에 저장되도록 했으며
DB에는 중복이 되지 않게 UUID를 이용한 난수 파일명과 업로드 파일명을 삽입했습니다.
<br>
### 파일을 DB에 저장 결과
![DB이미지](https://user-images.githubusercontent.com/83013198/151115570-b6c88879-38c2-4733-be07-c687fe8654b1.PNG)

---

![다운로드](https://user-images.githubusercontent.com/83013198/148331469-0e17d7d5-4231-465b-a14b-3489449af4bd.gif)
```java
    @GetMapping("/board/download")
    public void downloadAttachFile(@RequestParam(value = "id", required = false) final Long id, Model model, HttpServletResponse response) {

        if (id == null) throw new RuntimeException("올바르지 않은 접근입니다.");

        FileDto fileInfo = boardService.getFileDetail(id);
        if (fileInfo == null || "Y".equals(fileInfo.getDeleteYn())) {
            throw new RuntimeException("파일 정보를 찾을 수 없습니다.");
        }

        String uploadDate = fileInfo.getInsertTime().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String uploadPath = Paths.get("C:", "develop", "upload", uploadDate).toString();

        String filename = fileInfo.getOriginalName();
        File file = new File(uploadPath, fileInfo.getSaveName());

        try {
            byte[] data = FileUtils.readFileToByteArray(file);
            response.setContentType("application/octet-stream");
            response.setContentLength(data.length);
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");

            response.getOutputStream().write(data);
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드에 실패하였습니다.");

        } catch (Exception e) {
            throw new RuntimeException("시스템에 문제가 발생하였습니다.");
        }
    }
```
HttpServletResponse 객체를 사용해 사용자에게 응답함으로써 파일 다운로드를 처리합니다.
<br>
id를 파라미터로 받지 못 했을 때 그리고 boardService에서 가져온 fileInfo가 없거나 삭제됐으면 예외처리를 했습니다.

---
![댓글](https://user-images.githubusercontent.com/83013198/148725290-1890d998-3d13-4cc7-9b5b-251548a4c939.gif)

![검색](https://user-images.githubusercontent.com/83013198/148332075-fca27c98-d11b-4f85-937f-73ce6856c41a.gif)

![페이징](https://user-images.githubusercontent.com/83013198/148725287-4fc6b5ea-3f01-466a-846c-831e41dd4ab6.gif)

![이전 페이지 정보](https://user-images.githubusercontent.com/83013198/148725292-80b40a62-796e-4951-9d16-4a8a5cb275c6.gif)

![리다이렉트 메시지](https://user-images.githubusercontent.com/83013198/148725293-1b2e506a-49c5-4360-8ab1-3ad854cf5715.gif)


---
### 느낀 점
1.
2.
3.

