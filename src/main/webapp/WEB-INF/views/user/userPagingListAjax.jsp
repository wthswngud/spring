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

		$("#userListTbody").on("click", ".userTr", function() {
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
		
		//첫번째 페이지의 사용자 정보를 요청
// 		userPagingListAjax(1,10);
		userPagingListAjaxHtml(1,10);
	});
	function userPagingListAjaxHtml(page, pageSize){
		$.ajax({
			url : "/user/pagingListAjaxHtml",
			method : "post",
			data : "page="+page + "&pageSize=" + pageSize,
			success : function(d){
				var html = d.split("SEPERATORSEPERATOR");
				$("#userListTbody").html(html[0]);
				$(".pagination").html(html[1]);
			}
		})
	}
	
function userPagingListAjax(page, pageSize){
	$.ajax({
		url : "/user/pagingListAjax",
		method : "post",
		data : "page="+page + "&pageSize=" + pageSize,
		success : function(d){
			console.log(d);
			// data.data.userList;
			
			// 사용자 리스트
			var html = "";
			
			d.data.userList.forEach(function(user){
				//html 생성
				html += "<tr class='userTr' data-userId='"+user.userId+"'>";
				html += "<td class='userId'>"+user.userId+"</td>";
				html += "<td>"+user.name+"</td>";
				html += "<td>"+user.alias+"</td>";
				html += "</td>";
				
			});
			
			//페이지네이션
			var pHtml = "";
			var pageVO = d.pageVO;
			
			if(pageVO.page == 1)
				pHtml += "<li class='disabled'><span>«</span></li>";
			else
				pHtml += "<li><a href='javascript:userPagingListAjax("+(pageVO.page-1)+","+pageVO.pageSize + ");'>«</a></li>";
			
			for(var i=1; i<=d.data.paginationSize; i++){
				if(pageVO.page==i)
					pHtml += "<li class=active><span>"+i+"</span></li>";
				else
					pHtml += "<li><a href='javascript:userPagingListAjax("+i+","+ pageVO.pageSize+");'>"+i+"</a></li>";
			}
			
			if(pageVO.page ==d.paginationSize)
				pHtml +="<li class='disabled'><span>»</span></li>"
			else
				pHtml += "<li><a href='javascript:userPagingListAjax("+(pageVO.page+1)+","+pageVO.pageSize+");'>»</a></li>";
			
			
			console.log("html : ", html);
			console.log("pHtml : ", pHtml);
			$("#userListTbody").html(html);
			$(".pagination").html(pHtml);
		}
	});
}
</script>
<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자 페이징 리스트(tiles)</h2>

		<!-- 사용자 상세조회 : userId가 필요 -->
		<form id="frm" action="${cp}/user/user" method="get">
			<input type="hidden" id="userId" name="userId" />
		</form>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>유저아이디</th>
					<th>유저 이름</th>
					<th>별명</th>
				</tr>
			</thead>
			<tbody id="userListTbody">
				
			</tbody>
		</table>

		<a href="${cp}/user/form" class="btn btn-default pull-right">사용자
			등록</a>
		<div class="text-center">
			<ul class="pagination"></ul>
		</div>

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