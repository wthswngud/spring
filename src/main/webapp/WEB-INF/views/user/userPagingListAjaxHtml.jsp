<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach items="${data.userList}" var="user" varStatus="status">
	<tr class="userTr" data-userid="${user.userId}"
		data-name="${user.name}">
		<td class="userId">${user.userId}</td>
		<td>${user.name}</td>
		<td>${user.alias}</td>
	</tr>
</c:forEach>

SEPERATORSEPERATOR

<c:set var="pageVo" value="${pageVo}" scope="request" />

<c:if test="${pageVo.page==1}">
	<li class="disabled"><span>«</span></li>
</c:if>
<c:if test="${pageVo.page!=1 }">
	<li><a href="javascript:userPagingListAjaxHtml(${pageVo.page-1},);"><span>«</span></a></li>
</c:if>
${pageVo.pageSize}
<c:forEach begin="1" end="${data.paginationSize}" step="1" var="i"
	varStatus="String">
	<c:if test="${pageVo.page==i}">
		<li class=active><span>${i}</span></li>
	</c:if>
	<c:if test="${pageVo.page!=i}">
		<li><a href="javascript:userPagingListAjaxHtml(${i},${pageVo.pageSize})">${i}</a></li>
	</c:if>
</c:forEach>

asd    :: ${pageVo.page}

<c:choose>
	<c:when test="${pageVo.page == data.paginationSize}">
		<li class="disabled"><span>»</span></li>
	</c:when>
	<c:otherwise>
		<li><a href="javascript:userPagingListAjaxHtml(${pageVo.page+1},${pageVo.pageSize})"><span>»</span></a></li>
	</c:otherwise>
</c:choose>