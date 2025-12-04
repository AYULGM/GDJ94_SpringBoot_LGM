<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 현재위치는 / 이고, static 폴더는 스프링부트에서 인식못함, static까지는 /(루트) -->
<!-- 처음 요청이 들어오는곳은 서블릿인데 컨트롤러에서 css따로 지정안해도 static폴더로 감 -->
<!-- Front단 자원들은 전부다 static폴더에서 찾아라. 스프링부트 기본설정 -->
<!-- 공통적으로하는게 웬만하면 절대경로로 하거라. -->
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
<!-- summernote페이지를 보는데 나머지 링크들(부트스트랩불러오는 링크같은거)은 이미 가지고있으니 없는 링크1개와 맨아래 스크립트 링크 1개 추가 -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs4.min.css" rel="stylesheet">
</head>
<body id="page-top">
	<div id="wrapper">
		<!-- side bar -->
		<c:import url="/WEB-INF/views/template/sidebar.jsp"></c:import>
		<!-- side bar -->
		
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">	
            <!-- Main Content -->
            <div id="content">
            
            	<!-- topbar -->
				<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>
				<!-- topbar End -->
				
                <!-- Begin Page Content -->
                <div class="container-fluid">				
                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">${category} ${sub}</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                        <i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    <!-- Content Row -->
                    <div class="row">
                    
                    <!-- 생성한 contents 작성(내용이 바뀌는부분) -->
                    <!-- name은 DTO로 가니까 Setter의 이름과 동일하게 -->
                    <!-- action은 액션태그 자체를 안써도됨(안쓰면 현재URL로이동하니까) -->
                    <!-- (25.12.01) 파일의 용량이 크니 잘개 쪼개서 받는다. -->
                    
             <!------------------------ Form --------------------- -->
             
             <!-- modelAttribute엔 겟맵핑에서 보낸 모델 어트리뷰트 (첫글자가 소문자인 클래스명)-->
             <form:form modelAttribute="dto" method="post" enctype="multipart/form-data">
        <%-- <form method="post" enctype="multipart/form-data"> --%>
             <!-- path엔 받아온 DTO의 setter,getter의 이름을 써라. -->
             	<form:hidden path="boardNum"/>
           <%-- <input type="hidden" name="boardNum" value="${dto.boardNum}"> --%>
					<!-- 개발자도구 열어보면 value값이 들어간게 보인다. -->
					  <div class="form-group">
					  <!-- 이건 굳이 쓸일없어서 주석처리 -->
					  <%-- <form:label path="boardWriter"></form:label> --%>
					    <label for="writer">작성자</label>
					    <!-- path가 name이 되서 쓸 필요없음 -->
					    <form:input path="boardWriter" cssClass="form-control" id="writer"/>
				<%-- 	<input type="text" class="form-control" value="${dto.boardWriter}" id="writer" name="boardWriter"> --%>					    <small id="emailHelp" class="form-text text-muted">name 또는 nickname을 입력하시면 됩니다.</small>
					  </div>
					  <div class="form-group">
					    <label for="title">글 제목</label>
					    <!-- path가 name이 된다. -->
					    <form:input path="boardTitle" cssClass="form-control" id="title"/>
				   <%-- <input type="text" class="form-control" value="${dto.boardTitle}" id="title" name="boardTitle"> --%>
				   		<form:errors path="boardTitle"></form:errors>
				   		<!-- 아무것도 설정안하면 기본 메시지가 뜸 -->
					  </div>
					  <div class="form-group">
					    <label for="contents">글 내용</label>
					    <!-- 줄바꿈(\n)을 그대로 출력하고 싶으면 <textarea>를 쓰던가, summernote() CDN을 불러오던가. -->
					    <form:textarea path="boardContents" cssClass="form-control" rows="8" id="contents"/>
				   <%-- <textarea class="form-control" id="contents" rows="3" name="boardContents">${dto.boardContents}</textarea> --%>
					  </div>
					  
					  <div class="form-group">
					  	<button type="button" id="fileAdd" class="form-control btn btn-primary">File Add</button>
					  </div>
					  
					  <div id="files" class="form-group">
					  	<!-- File Add 버튼을 누르면 여기에 JS로 인해 파일들이 올라감 -->
					  </div>
					  
					  <button type="submit" class="btn btn-primary">Submit</button>
					</form:form>
                    
                    </div>
				</div>
                <!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->
            
            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->
        </div>
	</div>

<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs4.min.js"></script>
<script type="text/javascript">
	$("#contents").summernote();
</script>
<script src="/js/board/board.js"></script>
</body>
</html>