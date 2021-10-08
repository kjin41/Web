<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서</title>
</head>
<body>
<div align="center">
<h3>도서관리</h3>
<a href="<%= root %>/book/regist.jsp">도서 등록</a><br>
<a href="<%= root %>/bookservlet?act=list">도서 목록</a><br>
</div>
</body>
</html>