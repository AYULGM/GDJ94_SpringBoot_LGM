<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Product</title>
<c:import url="/WEB-INF/views/template/head.jsp"/>
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
                        <h1 class="h3 mb-0 text-gray-800">Product Detail</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row">
                    
                    <!-- 생성한 contents 작성 -->
					<div class="col-12">
					    <div class="card shadow p-4">
					
					        <!-- 상품 카테고리 -->
					        <div class="text-primary font-weight-bold text-uppercase mb-2" style="font-size: 0.9rem;">
					            ${dto.productCategory}
					        </div>
					
					        <!-- 상품명 -->
					        <h2 class="font-weight-bold text-gray-800 mb-3">
					            ${dto.productName}
					        </h2>
					
					        <!-- 금리 -->
					        <div class="mb-3" style="font-size: 1.2rem; font-weight: 600; color:#4e73df;">
					            연 ${dto.productRate}%
					        </div>
					
					        <!-- 판매 상태 -->
					        <c:choose>
					            <c:when test="${dto.productSale == false}">
					                <span class="badge badge-success mb-4" style="font-size:1rem;">판매중</span>
					            </c:when>
					            <c:otherwise>
					                <span class="badge badge-secondary mb-4" style="font-size:1rem;">판매중지</span>
					            </c:otherwise>
					        </c:choose>
					
					        <hr>
					
					        <!-- 상세 설명 -->
					        <h6 class="font-weight-bold text-gray-700 mb-2">상품 설명</h6>
					        <p class="text-gray-900" style="line-height: 1.7;">
					            ${dto.productContents}
					        </p>
					
					        <hr>
					
					        <!-- 버튼 영역 -->
					        <div class="d-flex justify-content-between mt-3">
					
					            <a href="./list" class="btn btn-secondary">
					                ← 목록으로
					            </a>
					
					            <div>
					                <a href="./update?productNum=${dto.productNum}" 
					                   class="btn btn-warning text-dark mr-2">
					                    수정하기
					                </a>
									<form action="./delete" method="post" class="d-inline"
									      onsubmit="return confirm('정말 삭제하시겠습니까?');">
									
									    <input type="hidden" name="productNum" value="${dto.productNum}">
									
									    <button type="submit" class="btn btn-danger">
									        삭제
									    </button>
									</form>
					            </div>
					
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
	

	<c:import url="/WEB-INF/views/template/foot.jsp"/>
	
	
	
	
	
	
</body>
</html>
