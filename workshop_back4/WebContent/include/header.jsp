<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.backend.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<c:if test="${!empty msg }">
<script>
	alert("${msg}");
</script>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SSAFY 도서 관리</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        mark.sky {
            background: linear-gradient(to top, #54fff9 20%, transparent 30%);
        }
        hr{
        	border: solid black;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginBtn").click(function () {
                if (!$("#id").val()) {
                    alert("아이디 입력!!!!");
                    return;
                } else if (!$("#pass").val()) {
                    alert("비밀번호 입력!!!!");
                    return;
                } else {
                    $("#writeform").attr("action", "${root}/user").submit();
                }

            });
            
        });
    </script>
</head>

<body>
<div class="col-lg-11 mx-auto">
	<h1 align=center>SSAFY 도서관리</h1><br>
	<c:if test="${empty userinfo}">
	<form align=right id="writeform" method="post" action="">
		<input type="hidden" name="act" value="login">
		아이디 : <input type="text" id="id" name="id" placeholder="아이디">
		비밀번호: <input type="password" id="pass" name="pass" placeholder="비밀번호">
		<button type="button" id="loginBtn">로그인</button>
	</form>
	</c:if>
	
	<c:if test="${!empty userinfo}">
	<div align="right">${userinfo.name }님 안녕하세요. <a href="${root }/user?act=logout">로그아웃</a></div>
	</c:if>
	<hr>
</div>

