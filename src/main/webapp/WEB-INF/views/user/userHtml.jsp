<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
name : <input type="text" readonly value="${userVO.name}"/><br><br>
alias : <input type="text" readonly value="${userVO.alias}"/><br><br>
birth : <input type="text" readonly value="<fmt:formatDate value="${userVO.birth}" pattern="yyyy-MM-dd"/>"/>
</body>
</html>