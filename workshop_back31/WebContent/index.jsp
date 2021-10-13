<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
</head>
<body>
  <div class="header">
    <h3>SSAFY JSP</h3>
    <hr color="black">
  </div>
<div align="center">
<h3>명함 관리</h3>
<a href="<%= root %>/namecard/regist.jsp">명함 등록</a><br>
<a href="<%= root %>/ncservlet?act=list">명함 목록</a><br>
</div>
  
<%@ include file="/include/footer.jsp" %>