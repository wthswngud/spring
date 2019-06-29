<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.userTr:hover {
	cursor: pointer;
	background: lime;
}
</style>

<script>
	$(document).ready(function() {
		//사용자 tr 태그 이벤트 등록
		var msg = '${msg}';
		if (msg != '') {
			alert(msg);
			msg = "";
		}

		$(".userTr").on("click", function() {
			console.log("userTr click");
			//userId를 획득하는 방법
			//$(this).find(".userId").text();
			//$(this).data("userid");

			//사용자 아이디를 #userId 값으로 설정해주고
			var userId = $(this).find(".userId").text();

			//#frm을 이용하여 submit();
			$("#userId").val(userId);
			$("#frm").submit();
		})

		$("#userRegBtn").on("click", function() {
			$("#download").submit();
		})
	})
</script>
<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자 페이징 리스트(tiles)</h2>

		<!-- 사용자 상세조회 : userId가 필요 -->
		<form id="frm" action="${cp}/user/user" method="get">
			<input type="hidden" id="userId" name="userId" />
		</form>

		<table class="table table-striped">
			<tr>
				<th>유저아이디</th>
				<th>유저 이름</th>
				<th>별명</th>
			</tr>
			<c:forEach items="${userList}" var="user" varStatus="status">
				<tr class="userTr" data-userid="${user.userId}"
					data-name="${user.name}">
					<td class="userId">${user.userId}</td>
					<td>${user.name}</td>
					<td>${user.alias}</td>
				</tr>
			</c:forEach>
		</table>

		<!-- 사용자 수 : 105건
			  페이지네이션 : 11건 
		-->
		<div class="text-center">
			<ul class="pagination">

				<c:set var="pageVo" value="${pageVo}" scope="request" />

				<c:if test="${pageVo.page==1}">
					<li class="disabled"><span>«</span></li>
				</c:if>
				<c:if test="${pageVo.page!=1 }">
					<li><a
						href="${cp}/user/pagingList?page=${requestScope.pageVo.page-1}&pageSize=${requestScope.pageVo.pageSize}"><span>«</span></a></li>
				</c:if>

				<c:forEach begin="1" end="${paginationSize}" step="1" var="i"
					varStatus="String">
					<c:if test="${pageVo.page==i}">
						<li class=active><span>${i}</span></li>
					</c:if>
					<c:if test="${pageVo.page!=i}">
						<li><a
							href="${cp}/user/pagingList?page=${i}&pageSize=${pageVo.pageSize}">${i}</a></li>
					</c:if>
				</c:forEach>

				<c:choose>
					<c:when test="${pageVo.page == paginationSize}">
						<li class="disabled"><span>»</span></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${cp}/user/pagingList?page=${pageVo.page+1}&pageSize=${pageVo.pageSize}"><span>»</span></a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<a href="${cp}/user/form" class="btn btn-default pull-right">사용자
			등록</a>

		<form id="download" action="/user/userListExcel">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="hidden" name="filename" value="userList" />
					<button id="userRegBtn" type="button"
						class="btn btn-default pull-right">엑셀 다운</button>
				</div>
			</div>
		</form>
	</div>
	<!-- /.blog-main -->
</div>
</html>