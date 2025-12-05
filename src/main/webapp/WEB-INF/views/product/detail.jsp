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
					
							<div id="list" data-product-num="${dto.productNum}">

							</div>
							
					        <!-- 버튼 영역 -->
					        <div class="d-flex justify-content-between mt-3">
					
					            <a href="./list" class="btn btn-secondary">
					                ← 목록으로
					            </a>
					
					<!-- 상품 디테일페이지에서 댓글 오른쪽편에 수정,삭제버튼을 만드는데 -->
					<!-- 일단은 로그인 여부 상관없이 모든 사용자에게 보여주게 하라고함. -->
					<!-- ajax, 댓글 수정은 아래 버튼태그에있는 data-toggle="modal" data-target="#commentModal" 또는 강제로 버튼 클릭하게 하는 JS를 넣으면됨 -->
					<!-- ajax, 댓글 삭제 -->
					            <div>
					            <!-- 상품 디테일창에서 장바구니 버튼만들었음, 마이페이지에도 만들어야함 -->
					            <!-- ajax(비동기)로 장바구니등록하게 해야함 , alert(confirm)창으로 상품이 등록되었다. -> 장바구니로 이동할거냐?(장바구니목록 이동(jsp만들어야함)) 계속 쇼핑할거냐?(현재 페이지 그대로) -->
					            	<button class="btn btn-danger text-white mr-2" >장바구니</button>
					            	<button class="btn btn-secondary text-white mr-2" data-toggle="modal" data-target="#commentModal">댓글달기</button>
					            	
					            		
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
	
		<!-- Modal (숨겨져 있다가 클릭하면 나오는 모달창) -->
		<div class="modal fade" id="commentModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form method="post">
		        	<input type="hidden" value="${dto.productNum}">
		        	<textarea id=contents rows="" cols="" name="boardContents"></textarea>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" id="close" class="btn btn-secondary" data-dismiss="modal">취소</button>
		        <button type="button" id="commentAdd" class="btn btn-primary">댓글 등록</button>
		      </div>
		    </div>
		  </div>
		</div>

	<c:import url="/WEB-INF/views/template/foot.jsp"/>
	<script type="text/javascript" src="/js/product/product_comment.js"></script>
	
	
	
	
	
</body>
</html>
