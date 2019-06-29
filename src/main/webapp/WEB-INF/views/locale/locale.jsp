<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script>
	$(document).ready(function(){
// 		$("select[name=lang]").val("${param.lang == null ? 'ko' : param.lang}");
		$("select[name=lang]").val("${lang}");
		
		
		$("select[name=lang]").on("change", function(){
			console.log("select box changed");
			$("select[name=lang]").parent().submit();
		})
	});
</script>
lang : ${param.lang}<br><br>
<form  action="/locale/view" method="post">
	<select name="lang">
		<option selected="selected" value="ko">한국어</option>
		<option value="en">English</option>
		<option value="ja">日本語</option>
	</select>
</form>
GREETING : <spring:message code="GREETING"/><br>
VISITOR : <spring:message code="VISITOR">
		  	<spring:argument value="brown"/>
 		  </spring:message>