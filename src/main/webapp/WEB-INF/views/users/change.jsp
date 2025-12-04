<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
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
            	<!-- topbar -->
            	
            	<!-- Begin Page Content -->
                <div class="container-fluid">
                	<!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Add Form</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row justify-content-center mt-5">
                    <div class="col-lg-6 mt-5">

                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">${category}</h6>
                                </div>
                                <div class="card-body">
                                <!-- modelAttribute엔 클래스명의 첫글자를 소문자로바꾼것이 들어간다. -->
                                   <form:form modelAttribute="userDTO" method="post" enctype="multipart/form-data">
                                   <%-- <form method="post" enctype="multipart/form-data"> --%>
                                   	  
                                   	  <!-- 패스워드 변경을 위한 페이지. -->
									  <div class="form-group">
									    <label for="password">기존 Password</label>
									    <!-- 여긴 Spring form:input으로 안쓴 이유가 따로따로 살리려고하는거임, 이렇게 안쓰려면 DB에 따로 기존PW를 모아두는 컬럼을 만들어야함 -->
									    <input type="password" class="form-control" name="exist">
									  </div>									  
                                   	  
									  <div class="form-group">
									    <label for="password">새로운 Password</label>
									    <form:input path="password" cssClass="form-control" id="password"/>
									    <form:errors path="password"></form:errors>
									    <!-- <input type="password" class="form-control"  id="password" name="password"> -->
									  </div>									  
									  <div class="form-group">
									    <label for="passwordcheck">새로운 PasswordCheck</label>
									    <form:input path="passwordCheck" cssClass="form-control" id="passwordCheck"/>
									    <form:errors path="passwordCheck"></form:errors>
									    <!-- <input type="password" class="form-control"  id="password" name="password"> -->
									  </div>									  
									  
									  <button type="submit" class="btn btn-primary">Submit</button>
									  </form:form>
									<%-- </form> --%>
                                </div>
                             </div>
                             
                     </div>           
                                        
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
	$("#contents").summernote()
</script>
<script type="text/javascript" src="/js/board/board.js"></script>

</body>
</html>