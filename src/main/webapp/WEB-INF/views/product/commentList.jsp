<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div>

<table>
<c:forEach items="${list}" var="v">
	<tr>
		<td>${v.username}</td>	
		<td>${v.boardContents}</td>	
		<td>${v.boardDate}</td>	
	</tr>
</c:forEach>
</table>
</div>

                    <div class="row justify-content-between col-sm-8 offset-sm-2">
						<nav aria-label="Page navigation example">
						  <ul class="pagination">
						    <li class="page-item">
						      <button class="page-link" data-pager-num=${pager.begin - 1} aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </button>
						    </li>
						    	<c:forEach begin="${pager.begin}" end="${pager.end}" var="i">
						    	<!-- pager에서 꺼내도 되고 파라미터로 보내니까 param.kind라고 해도됨 -->
					    		<li class="page-item">
						    		<button class="page-link" data-pager-num="${i}">${i}</button>
					    		</li>
						    	</c:forEach>
						    <li class="page-item">
						      <button class="page-link" data-pager-num=${pager.end + 1} aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </button>
						    </li>
						  </ul>
						</nav>
                    </div>
                    