<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>upload.jsp</h2>
	<form action="${cp}/file/upload" method="post" enctype="multipart/form-data">
		<input type="file" name="img"/>
		<input type="submit" name="전송"/>
	</form>
</body>
</html>