<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	if(session!=null){
    		session.invalidate();
    	}
    %>
    <script>
    alert("로그아웃 완료!");
    location.href="../main.jsp";
    </script>