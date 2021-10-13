<%@page import="com.ssafy.product.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String root=request.getContextPath();
UserDto userDto=(UserDto) session.getAttribute("userinfo");
%>

<%
String msg=(String)request.getAttribute("msg");
if (msg!=null){
%>
<script type="text/javascript">
	alert("<%=msg%>");
</script>
<%} %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>hwbackend03</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        mark.sky {
            background: linear-gradient(to top, #54fff9 20%, transparent 30%);
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>