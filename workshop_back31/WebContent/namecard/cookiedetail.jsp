<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.ssafy.namecard.dto.*"
	import="java.util.*"%>
<%@ include file="/include/header.jsp"%>

<%
Cookie[] cookies=request.getCookies();
String[] ncinfo=new String[9];
if (cookies != null) {
	for (Cookie cookie : cookies) {
		if(cookie.getName().equals("name")){
			ncinfo[0]=cookie.getValue();
		}
		if(cookie.getName().equals("company")){
			ncinfo[1]=cookie.getValue();
		}
		if(cookie.getName().equals("part")){
			ncinfo[2]=cookie.getValue();
		}
		if(cookie.getName().equals("email")){
			ncinfo[3]=cookie.getValue();
		}
		if(cookie.getName().equals("phone1")){
			ncinfo[4]=cookie.getValue();
		}
		if(cookie.getName().equals("phone2")){
			ncinfo[5]=cookie.getValue();
		}
		if(cookie.getName().equals("phone3")){
			ncinfo[6]=cookie.getValue();
		}
		if(cookie.getName().equals("address1")){
			ncinfo[7]=cookie.getValue();
		}
		if(cookie.getName().equals("address2")){
			ncinfo[8]=cookie.getValue();
		}
	}
}

%>
<script type="text/javascript">
	$(document).ready(function () {
	    $("#mvRegisterBtn").click(function () {
	        location.href = "<%=root%>/namecard/regist.jsp";
		});
	    $("#mvListBtn").click(function () {
	        location.href = "<%=root%>/ncservlet?act=list";
		});
	});
</script>
</head>
<body>
	<div class="header">
		<h3>SSAFY JSP</h3>
		<hr color="black">
	</div>
	<div class="container text-center mt-3">
		<div class="col-lg-8 mx-auto">
			<h2 class="p-3 mb-3 shadow bg-light">
				<mark class="sky">명함 목록</mark>
			</h2>
			<div class="m-3 text-right">
				<button type="button" id="mvRegisterBtn" class="btn btn-link">명함 등록</button>
				<button type="button" id="mvListBtn" class="btn btn-link">명함 목록</button>
			</div>

			<table class="table table-active text-left">
				<tbody>
					<tr class="table-info">
						<td>이름 : <%=ncinfo[0]%></td>
					</tr>
					<tr class="table-info">
						<td>회사 : <%=ncinfo[1]%></td>
					</tr>
					<tr class="table-info">
						<td>부서 : <%=ncinfo[2]%></td>
					</tr>
					<tr class="table-info">
						<td>이메일 : <%=ncinfo[3]%></td>
					</tr>
					<tr class="table-info">
						<td>전화번호 : <%=ncinfo[4]%>-<%=ncinfo[5]%>-<%=ncinfo[6]%></td>
					</tr>
					<tr class="table-info">
						<td>주소 : <%=ncinfo[7]%> <%=ncinfo[8]%></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<%@ include file="/include/footer.jsp"%>