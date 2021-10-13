<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*, java.util.*"%>
<%@ include file="/include/header.jsp" %>
<%
List<File> list=(List<File>)request.getAttribute("imglist");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	if (list!=null && list.size()!=0){
		for (File f: list) {
	%>
	<%= f.getName() %><br>
	<img src="<%= root %>/images/<%= f.getName()%>" width=300px><br><br>
	
	<%
		}
	} else {
	%>
	<h3>사진 목록이 없습니다.</h3>
	<%
	}
	%>
</body>
</html>
