<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
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
                                   <form method="post" enctype="multipart/form-data">
                                   	  
									  <div class="form-group">
									    <label for="writer">Username</label>
									    <input type="text" class="form-control"  id="username" name="username">
									  </div>
									  <div class="form-group">
									    <label for="password">Password</label>
									    <input type="password" class="form-control"  id="password" name="password">
									  </div>									  
									  <div class="form-group">
									    <label for="name">Name</label>
									    <input type="text" class="form-control" name="name" id="name">
									  </div>
									  <div class="form-group">
									    <label for="email">Email</label>
									    <input type="email" class="form-control" name="email" id="email">
									  </div>									  
									  <div class="form-group">
									    <label for="phone">Phone</label>
									    <input type="text" class="form-control" name="phone" id="phone">
									  </div>									  
									  <div class="form-group">
									    <label for="birth">Birth</label>
									    <input type="date" class="form-control" name="birth" id="birth">
									  </div>									  
									  <div class="form-group">
									  	<button type="button" id="fileAdd"  class="form-control btn btn-primary" >File Add</button>
									  	
									  </div>
									  
									  <div id="files" class="form-group">
									  
									  </div>
									
									  <button type="submit" class="btn btn-primary">Submit</button>
									</form>
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