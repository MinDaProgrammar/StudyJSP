<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="testPro.jsp" method="post">
		<table border="1">
		<tr>
			<th>번호</th>
			<td><input type="text" name="idx"><br>
		</tr>

		<tr>
			<th>이름</th>
			<td><input type="text" name="name"><br>
		</tr>
		
		<tr>
			<td colspan="2"><input type="submit" value="전송"></td>
		</tr>
		</table>
	</form> 
</body>
</html>