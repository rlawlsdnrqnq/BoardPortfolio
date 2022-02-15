# MybatisBoard Project
---
## 목차
1. [**개발 목적**](#개발-목적)

2. [**개발 환경**](#개발-환경)

3. [**프로젝트 구조**](#프로젝트-구조)

4. [**기능**](#기능)

5. [**느낀점**](#느낀-점)

---
## 개발 목적
* Web 개발자가 되기 위한 기본기 다지기
* MVC 구조 이해
* 개인 역량 어필
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
기본적인 CRUD 글생성, 글읽기, 글수정, 글삭제는 포트폴리오에서 제외했습니다.

---
### 알러트 메시지
![알러트 메시지](https://user-images.githubusercontent.com/83013198/148725293-1b2e506a-49c5-4360-8ab1-3ad854cf5715.gif)
```javascript
<th:block layout:fragment="script">
	<script th:inline="javascript">
	/* <![CDATA[ */

		window.onload = function() {
			var message = /*[[ ${message} ]]*/;
			if (isEmpty(message) == false) {
				alert(message);
			}

			var params = /*[[ ${params} ]]*/;
			if (isEmpty(params) == false) {
				document.dataForm.submit();
			} else {
				var redirectUri = /*[[ ${redirectUri} ]]*/;
				location.href = redirectUri;
			}
		}
		/*[- end of onload -]*/

	/* ]]> */
	</script>
</th:block>
```
컨트롤러에서 전달받은 메시지가 비어있지 않으면 사용자에게 Alert 메시지를 보여줍니다.
<br>
params가 비어있지 않으면 폼을 컨트롤러로 submit합니다. 비어있으면 redirectUri에 지정된 URI를 호출합니다.

---
### 페이징
![페이징](https://user-images.githubusercontent.com/83013198/153809370-621db8f6-256c-4f0d-a1d5-4488e02f9953.gif)
```java
@Getter
@Setter
public class Criteria {

	/** 현재 페이지 번호 */
	private int currentPageNo;

	/** 페이지당 출력할 데이터 개수 */
	private int recordsPerPage;

	/** 화면 하단에 출력할 페이지 사이즈 */
	private int pageSize;

	/** 검색 키워드 */
	private String searchKeyword;

	/** 검색 유형 */
	private String searchType;

	public Criteria() {
		this.currentPageNo = 1;
		this.recordsPerPage = 10;
		this.pageSize = 10;
	}

	public int getStartPage() {
		return (currentPageNo - 1) * recordsPerPage;
	}

}
```
파라미터의 개수가 늘어난다거나 했을 때 파라미터의 관리와 수집이 까다로워지기 때문에 공통으로 사용할 수 있는 Criteria클래스를 만들었습니다.
<br>
```java
<ul class="pagination">
	<li th:if="*{hasPreviousPage == true}" th:onclick="movePage([[ ${#request.requestURI} ]], [[ ${params.makeQueryString(1)} ]])">
		<a href="javascript:void(0)" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
        </li>
</
```
thymeleaf를 사용하여 이전 페이지 정보가 없으면 페이징 아이콘을 숨겼습니다.

---
### 검색
![검색](https://user-images.githubusercontent.com/83013198/148332075-fca27c98-d11b-4f85-937f-73ce6856c41a.gif)
```javascript
<th:block layout:fragment="script">
    <script th:inline="javascript">
      /*<![CDATA[*/

      function movePage(uri, queryString) {
        location.href = uri + queryString;
      }

      function searchBoard(form) {
        //메인 검색
        if(isEmpty(form) == true) {
          var searchKeyword = document.getElementById("mainSearchKeyword");
          if(isEmpty(searchKeyword.value) == true) {
            alert("키워드를 입력해 주세요.");
            searchKeyword.focus();
            return false;
          }
          form = document.getElementById("searchForm");
          form.searchKeyword.value = searchKeyword.value;
          form.submit();
        }
        //드롭다운 검색
        if(isEmpty(form.searchKeyword.value) == true) {
          alert("키워드를 입력해 주세요.");
          form.searchKeyword.focus();
          return false;
        }
      }

      /*]]>*/
    </script>
  </th:block>
```
메인 검색과 드롭다운 검색을 처리하는 함수입니다. 메인에서 입력한 키워드만을 통해 검색이 이루어졌을 때 해당 함수의 첫 번째 if 문 안의 로직이 실행됩니다.
<br>
드롭다운 안에서의 검색은 이미 폼 태그로 감싸져 있으며 버튼의 타입이 submit으로 지정되어 있기 때문에 별 다른 처리 없이 컨트롤러로 폼 데이터를 전송합니다.

---
### 댓글
![댓글](https://user-images.githubusercontent.com/83013198/148725290-1890d998-3d13-4cc7-9b5b-251548a4c939.gif)
```java
function insertComment(boardIdx) {

	var content = document.getElementById("content");
	if (isEmpty(content.value) == true) {
		content.setAttribute("placeholder", "댓글을 입력해 주세요.");
		content.focus();
		return false;
	}

	var uri = /*[[ @{/comments} ]]*/;
	var headers = {"Content-Type": "application/json", "X-HTTP-Method-Override": "POST"};
	var params = {"boardIdx": boardIdx, "content": content.value, "writer": "관리자"};

	$.ajax({
		url: uri,
		type: "POST",
		headers: headers,
		dataType: "json",
		data: JSON.stringify(params),
		success: function(response) {
			if (response.result == false) {
				alert("댓글 등록에 실패하였습니다.");
				return false;
			}

			printCommentList();
			content.value = "";
		},
		error: function(xhr, status, error) {
			alert("에러가 발생하였습니다.");
			return false;
		}
	});
}
```

댓글은 ajax 비동기 방식으로 구현했습니다.
<br>
댓글 입력창인 input태그에 id를 지정하고, 지정한 id가 비어있으면 placeholder 속성을 지정한 후 ajax 방식으로 서버에 전송합니다.

---

### 파일 업로드
![업로드](https://user-images.githubusercontent.com/83013198/148327655-2e4697dc-84c9-4a4b-98d4-b4ed19954d06.gif)
업로드 파일은 서버 구동 폴더 밖 외부에 저장되도록 했으며
DB에는 중복이 되지 않게 UUID를 이용한 난수 파일명과 업로드 파일명을 삽입했습니다.
<br>
#### 파일을 DB에 저장 결과
![DB이미지](https://user-images.githubusercontent.com/83013198/151115570-b6c88879-38c2-4733-be07-c687fe8654b1.PNG)

---
### 파일 다운로드
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

id를 파라미터로 받지 못 했을 때 그리고 boardService에서 가져온 fileInfo가 없거나 삭제됐으면 예외처리를 했습니다.
<br>
HttpServletResponse 객체를 사용해 사용자에게 응답함으로써 파일 다운로드를 처리합니다.

---
### 느낀 점
1.Spring Security를 이용한 회원가입 기능을 추가하지 않아서 아쉽지만 실무능력이 필요한 시점이라고 판단하였습니다. 
2.버그 개선
3.혼자 하면서 느낀 점 협업의 중요성을 느낌
4.가장 필요한 능력이 멀까
5.테스트의 중요성

