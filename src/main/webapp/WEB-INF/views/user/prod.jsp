<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp" %>
</head>
<body>
	<!-- header -->
	<%@include file = "/WEB-INF/views/common/header.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<!-- left -->
			<%@include file="/WEB-INF/views/common/left.jsp" %>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">prod</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>Prod_ID</th>
									<th>Prod_NM</th>
									<th>Prod_Gu</th>
									<th>Prod_Buyer</th>
								</tr>
								
								<c:forEach begin="0" end="${pageList.size()-1}" step="1" var="i">
									<c:set var="prodVO" value="${pageList.get(i)}"/>
									<tr>
										<td>${prodVO.prod_id}</td>
										<td>${prodVO.prod_name}</td>
										<td>${prodVO.prod_lgu}</td>
										<td>${prodVO.prod_buyer}</td>
									</tr>
								</c:forEach>
							</table>
							
							<c:set var="pageVo" value="pageVo"/>
							
							
							<div class = "text-center">
								<ul class = "pagination">
									<c:if test="${pageVO.page==1}">
										<li class=disabled><span>«</span></li>
									</c:if>
									<c:if test="${pageVO.getPage()!=1}">
										<li><a href = "${cp}/prod/prod?page=${pageVO.page-1}&pageSize=${pageVO.pageSize}"><span>«</span></a></li>
									</c:if>
									
									
									<c:forEach begin="1" end="${pagenation}" step="1" var="i">
										<c:choose>
											<c:when test="${pageVO.page==i}">
												<li class = active><span>${i}</span></li>
											</c:when>
											<c:otherwise>
												<li><a href = "${cp}/prod/prod?page=${i}&pageSize=${pageVO.pageSize}">${i}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									
									
									<c:if test="${pageVO.page==pagenation}">
										<li class=disabled><span>»</span></li>
									</c:if>
									<c:if test="${pageVO.page!=pagenation}">
										<li><a href = "${cp}/prod/prod?page=${pageVO.page+1}&pageSize=${pageVO.pageSize}"><span>»</span></a></li>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>