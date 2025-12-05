<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Product Add</title>
    <c:import url="/WEB-INF/views/template/head.jsp"/>

    <style>
        .form-section {
            background: #fff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.08);
        }
        label {
            font-weight: 600;
        }
    </style>
</head>

<body id="page-top">

<div id="wrapper">

    <!-- Sidebar -->
    <c:import url="/WEB-INF/views/template/sidebar.jsp"/>
    <!-- Sidebar End -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <div id="content">

            <!-- Topbar -->
            <c:import url="/WEB-INF/views/template/topbar.jsp"/>
            <!-- Topbar End -->

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">${kind} Product</h1>

                    <a href="/product/list" class="btn btn-sm btn-secondary shadow-sm">
                        <i class="fas fa-list fa-sm text-white-50"></i> Back to List
                    </a>
                </div>

                <!-- Form Section -->
                <div class="form-section">

                    <form method="post">
						
						<!-- productNum -->
						<input type="hidden" value="${dto.productNum}" name="productNum">
                        <!-- Name -->
                        <div class="form-group mb-3">
                            <label>상품명</label>
                            <input type="text" class="form-control" name="productName" value="${dto.productName}" required>
                        </div>

                        <!-- Category -->
                        <div class="form-group mb-3">
                            <label>카테고리</label>
                            <input type="text" class="form-control" name="productCategory" value="${dto.productCategory}" required>
                        </div>

                        <!-- Rate -->
                        <div class="form-group mb-3">
                            <label>이율 (%)</label>
                            <input type="number" step="0.01" class="form-control" name="productRate" value="${dto.productRate}" required>
                        </div>

                        <!-- Contents -->
                        <div class="form-group mb-3">
                            <label>내용</label>
                            <textarea class="form-control" name="productContents" rows="5" required>${dto.productContents}</textarea>
                        </div>

                        <!-- Sale -->
                        <div class="form-group mb-4">
                            <label>판매 상태</label>
                            <select class="form-control" name="productSale">
                                <option value="0">판매중</option>
                                <option value="1">판매중지</option>
                            </select>
                        </div>

                        <!-- Submit -->
                        <button type="submit" class="btn btn-primary btn-block">
                            <i class="fas fa-save"></i> 상품 등록
                        </button>

                    </form>

                </div>
                <!-- END Section -->

            </div>

        </div>

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright © Your Website 2021</span>
                </div>
            </div>
        </footer>

    </div>
</div>

<c:import url="/WEB-INF/views/template/foot.jsp"/>

</body>
</html>
