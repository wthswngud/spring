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
						<h2 class="sub-header">Lprod</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>Lprod_ID</th>
									<th>Lprod_NM</th>
									<th>Lprod_gu</th>
								</tr>
								
								<c:forEach begin="0" end="${pageList.size()-1}" step="1" var="i">
									<c:set var="lprodVO" value="${pageList.get(i)}"/>
									<tr>
										<td>${lprodVO.lprod_id}</td>
										<td>${lprodVO.lprod_nm}</td>
										<td>${lprodVO.lprod_gu}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>