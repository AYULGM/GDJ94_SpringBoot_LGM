<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>

<style>
	.cart-img {
		width: 60px;
		height: 60px;
		border-radius: 10px;
		object-fit: cover;
		border: 1px solid #ddd;
	}
	.total-box {
		font-size: 1.2rem;
		font-weight: bold;
		padding: 15px;
		border-radius: 10px;
		background-color: #f7f7f7;
	}
</style>

</head>
<body id="page-top">

	<div id="wrapper">

		<!-- Sidebar -->
		<c:import url="/WEB-INF/views/tamplate/sidebar.jsp"></c:import>
		<!-- Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<c:import url="/WEB-INF/views/tamplate/topbar.jsp"></c:import>
				<!-- Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">장바구니</h1>
					</div>

					<!-- Cart Table -->
					<div class="row justify-content-center">
						<div class="col-lg-10">

							<div class="card shadow mb-4">
								<div class="card-header bg-warning text-dark py-3">
									<h6 class="m-0 font-weight-bold">담긴 금융상품</h6>
								</div>

								<div class="card-body">

									<table class="table table-hover align-middle">
										<thead style="background-color: #fff3cd;">
											<tr>
												<th>상품 번호</th>
												<th>상품명</th>
												<th>카테고리</th>
												<th>금리</th>
												<th>수량</th>
												<th>삭제</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${cartList}" var="item">
												<tr>
													<td>${item.productNum}</td>

													<!-- 상품명 -->
													<td>
														<a href="/product/detail?productNum=${item.productNum}">
															${item.productName}
														</a>
													</td>

													<td>${item.productCategory}</td>

													<td>
														<span class="badge" 
														      style="background-color: #ffd24c; font-size: 1rem;">
															★ ${item.productRate}
														</span>
													</td>

													<!-- 수량 입력 -->
													<td style="width: 120px;">
														<input type="number" class="form-control" min="1" value="${item.quantity}">
													</td>

													<!-- 삭제 버튼 -->
													<td>
														<a href="./delete?cartId=${item.cartId}"
														   class="btn btn-danger btn-sm">삭제</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

									<hr>

									<!-- Total Section -->
									<div class="total-box mt-4">
										총 상품 수: <strong>${totalCount}</strong> 개  
										&nbsp;&nbsp; | &nbsp;&nbsp;
										총 금리 평균: 
										<strong>${totalRate}</strong>
									</div>

									<!-- Buttons -->
									<div class="text-end mt-4">
										<a href="/product/list" class="btn btn-secondary">상품 목록</a>
										<a href="/order/checkout" class="btn btn-primary">결제하기</a>
									</div>

								</div>

							</div>

						</div>
					</div>

				</div>
				<!-- End Page Content -->

			</div>
			<!-- End Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2021</span>
					</div>
				</div>
			</footer>

		</div>
	</div>

	<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>

</body>
</html>