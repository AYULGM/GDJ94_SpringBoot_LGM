<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                        <h1 class="h3 mb-0 text-gray-800">Index</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                        <i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    <!-- Content Row -->
                    <div class="row">
                    
                    <div>
                    <!-- code엔 message properties에 들어있는 키(Key)를 적는다.  -->
                    
                    	<spring:message code="hello" text="키가없을때 기본 메시지"></spring:message>
                    </div>
                    
                    <!-- 생성한 contents 작성(내용이 바뀌는부분) -->
                    <!-- 인증되었다면(로그인 됐다) -->
                    <h1>Remote Test</h1>
                    <sec:authorize access="isAuthenticated()">
                    	<h1>Login 성공</h1>
                    	<!-- authentication은 사용자정보를 담고있음, principal은 Object라 형변환해줘야함, 근데 EL태그는 자동으로 그 객체를 따라감 -->
                    	<sec:authentication property="principal" var="user"/>
                    	
                    	<h1>${user.username}</h1>
                    	<h1>${user.email}</h1>
                    	<h3>
                    		<sec:authentication property="principal.phone"/>
                    	</h3>
                    	<h2>
                    		<!-- getName()과 같은효과 -->
                    		<sec:authentication property="name"/>
                    	</h2>
                    	
                    	<!-- 스코프명.변수명인데 스코프는 작은것부터 찾음, 그래서 바로위에 var="user"를 찾아감 -->
                    	<spring:message code="message.welcome" arguments="${user.username},${user.birth}" argumentSeparator="," var="m"></spring:message>
                    	<hr>
                    	<h3>${m}</h3>
                    </sec:authorize>
                    
                    <sec:authorize access="!isAuthenticated()">
                    	<h1>Login 필요</h1>
                    	<a href="/oauth2/authorization/kakao">카카오 로그인</a>
                    </sec:authorize>
                    
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
<script src="./js/index/index.js"></script>
</body>
</html>