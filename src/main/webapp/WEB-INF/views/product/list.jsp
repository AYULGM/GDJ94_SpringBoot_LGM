<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Product</title>
<c:import url="/WEB-INF/views/template/head.jsp"/>

<style>
    .product-card {
        text-decoration: none;
        color: inherit;
    }
    .product-card .card {
        transition: all 0.2s ease;
        cursor: pointer;
    }
    .product-card .card:hover {
        transform: translateY(-5px);
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
    }
</style>



</head>
<body id="page-top">

	<div id="wrapper">
		<!-- side bar -->
		<c:import url="/WEB-INF/views/template/sidebar.jsp"/>
		<!-- side bar -->
		
		<!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
       			
       			<!-- topbar -->
       			<c:import url="/WEB-INF/views/template/topbar.jsp"/>
            	<!-- topbar -->
            	
            	<!-- Begin Page Content -->
                <div class="container-fluid">
                	<!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
					    <h1 class="h3 mb-0 text-gray-800">Product</h1>
					
					    <div>
					        <a href="add" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
					            <i class="fas fa-plus fa-sm text-white-50"></i> Add Product
					        </a>
					
					        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-secondary shadow-sm">
					            <i class="fas fa-download fa-sm text-white-50"></i> Generate Report
					        </a>
					    </div>
					</div>

                    
                    <!-- Content Row -->
                    <div class="row">
                    
					    <c:forEach items="${dto}" var="p">
					
					        <div class="col-xl-3 col-md-6 mb-4">
					
					            <!-- 전체 카드 클릭 -->
					            <a href="./detail?productNum=${p.productNum}" class="product-card">
					
					                <div class="card border-left-primary shadow h-100 py-3 px-3">
					
					                    <!-- Category -->
					                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
					                        ${p.productCategory}
					                    </div>
					
					                    <!-- Name -->
					                    <div class="h5 mb-2 font-weight-bold text-gray-800">
					                        ${p.productName}
					                    </div>
					
						                <!-- Product Rate -->
										<div class="text-primary mb-3" style="font-weight: 600;">
										    연 ${p.productRate}% 
										</div>
						
					                    <!-- Sale Status -->
					                    <c:if test="${p.productSale == false}">
					                        <span class="badge badge-success">판매중</span>
					                    </c:if>
					                    <c:if test="${p.productSale == true}">
					                        <span class="badge badge-secondary">판매중지</span>
					                    </c:if>
					
					                </div>
					
					            </a>
					
					        </div>
					
					    </c:forEach>

                    
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
	

	<c:import url="/WEB-INF/views/template/foot.jsp"/>
	
	
	
	
	
	
</body>
</html>
