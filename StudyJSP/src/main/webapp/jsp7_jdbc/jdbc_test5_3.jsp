<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>jdbc_test5.jsp</h1>
	<%
	String driver= "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/study_jsp2";
	String user = "root";
	String password="1234";
	
	Class.forName(driver);
	System.out.println("드라이버 로드 성공!");
	
	Connection con = DriverManager.getConnection(url,user,password);
	System.out.println("DB연결 성공!");
	
	int idx=100;
	String sql="DELETE FROM test WHERE idx=?";
	PreparedStatement pstmt = con.prepareStatement(sql);
	pstmt.setInt(1, idx);

	
	int deleteCount=pstmt.executeUpdate();
	out.println("<h3>"+deleteCount+"개 레코드 삭제 성공!</h3>");
	%>
</body>
</html>